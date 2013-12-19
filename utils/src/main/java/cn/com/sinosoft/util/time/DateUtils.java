/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.sinosoft.util.time;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sinosoft.util.string.StringUtils;

/**
 * <p>
 * A suite of utilities surrounding the use of the {@link java.util.Calendar}
 * and {@link java.util.Date} object.
 * </p>
 * 
 * <p>
 * DateUtils contains a lot of common methods considering manipulations of Dates
 * or Calendars. Some methods require some extra explanation. The truncate,
 * ceiling and round methods could be considered the Math.floor(), Math.ceil()
 * or Math.round versions for dates This way date-fields will be ignored in
 * bottom-up order. As a complement to these methods we've introduced some
 * fragment-methods. With these methods the Date-fields will be ignored in
 * top-down order. Since a date without a year is not a valid date, you have to
 * decide in what kind of date-field you want your result, for instance
 * milliseconds or days.
 * </p>
 * 
 * 
 * 
 * @author Apache Software Foundation
 * @author <a href="mailto:sergek@lokitech.com">Serge Knystautas</a>
 *  k Bogucki
 * @author <a href="mailto:ggregory@seagullsw.com">Gary Gregory</a>
 * @author Phil Steitz
 * @author Robert Scholte
 * @since 2.0
 * @version $Id: DateUtils.java 911986 2010-02-19 21:19:05Z niallp $
 */
@SuppressWarnings({ "rawtypes" })
public class DateUtils {

	/**
	 * The UTC time zone (often referred to as GMT).
	 */
	public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
	/**
	 * Number of milliseconds in a standard second.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_SECOND = 1000;
	/**
	 * Number of milliseconds in a standard minute.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	/**
	 * Number of milliseconds in a standard hour.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	/**
	 * Number of milliseconds in a standard day.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	/**
	 * This is half a month, so this represents whether a date is in the top or
	 * bottom half of the month.
	 */
	public final static int SEMI_MONTH = 1001;

	// 默认显示日期的格式
	public static final String DATEFORMAT_STR = "yyyy-MM-dd";

	// 默认显示日期时间的格式
	public static final String DATETIME_STR = "yyyy-MM-dd HH:mm:ss";

	// 默认显示日期的格式
	public static final String YYYY_MM_DATEFORMAT_STR = "yyyy-MM";

	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATEFORMAT_STR = "yyyy年MM月dd日";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATETIME_STR = "yyyy年MM月dd日HH时mm分ss秒";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR_YYYYMMDDHHMM = "yyyy年MM月dd日HH时mm分";

	private static DateFormat dateFormat = null;

	private static DateFormat dateTimeFormat = null;

	private static DateFormat zhcnDateFormat = null;

	private static DateFormat zhcnDateTimeFormat = null;

	static {
		dateFormat = new SimpleDateFormat(DATEFORMAT_STR);
		dateTimeFormat = new SimpleDateFormat(DATETIME_STR);
		zhcnDateFormat = new SimpleDateFormat(DATETIME_STR);
		zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_DATETIME_STR);
	}

	private static final int[][] fields = { { Calendar.MILLISECOND }, { Calendar.SECOND }, { Calendar.MINUTE }, { Calendar.HOUR_OF_DAY, Calendar.HOUR }, { Calendar.DATE, Calendar.DAY_OF_MONTH, Calendar.AM_PM
	/* Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK_IN_MONTH */
	}, { Calendar.MONTH, DateUtils.SEMI_MONTH }, { Calendar.YEAR }, { Calendar.ERA } };

	/**
	 * A week range, starting on Sunday.
	 */
	public final static int RANGE_WEEK_SUNDAY = 1;

	/**
	 * A week range, starting on Monday.
	 */
	public final static int RANGE_WEEK_MONDAY = 2;

	/**
	 * A week range, starting on the day focused.
	 */
	public final static int RANGE_WEEK_RELATIVE = 3;

	/**
	 * A week range, centered around the day focused.
	 */
	public final static int RANGE_WEEK_CENTER = 4;

	/**
	 * A month range, the week starting on Sunday.
	 */
	public final static int RANGE_MONTH_SUNDAY = 5;

	/**
	 * A month range, the week starting on Monday.
	 */
	public final static int RANGE_MONTH_MONDAY = 6;

	/**
	 * Constant marker for truncating
	 */
	private final static int MODIFY_TRUNCATE = 0;

	/**
	 * Constant marker for rounding
	 */
	private final static int MODIFY_ROUND = 1;

	/**
	 * Constant marker for ceiling
	 */
	private final static int MODIFY_CEILING = 2;

	/**
	 * <p>
	 * <code>DateUtils</code> instances should NOT be constructed in standard
	 * programming. Instead, the class should be used as
	 * <code>DateUtils.parse(str);</code>.
	 * </p>
	 * 
	 * <p>
	 * This constructor is public to permit tools that require a JavaBean
	 * instance to operate.
	 * </p>
	 */
	public DateUtils() {
		super();
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if two date objects are on the same day ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2002 13:45 would return false.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if two calendar objects are on the same day ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2002 13:45 would return false.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if two date objects represent the same instant in time.
	 * </p>
	 * 
	 * <p>
	 * This method compares the long millisecond time of the two objects.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameInstant(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return date1.getTime() == date2.getTime();
	}

	/**
	 * <p>
	 * Checks if two calendar objects represent the same instant in time.
	 * </p>
	 * 
	 * <p>
	 * This method compares the long millisecond time of the two objects.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if two calendar objects represent the same local time.
	 * </p>
	 * 
	 * <p>
	 * This method compares the values of the fields of the two objects. In
	 * addition, both calendars must be the same of the same type.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.MILLISECOND) == cal2.get(Calendar.MILLISECOND) && cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND) && cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE) && cal1.get(Calendar.HOUR) == cal2.get(Calendar.HOUR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.getClass() == cal2.getClass());
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Parses a string representing a date by trying a variety of different
	 * parsers.
	 * </p>
	 * 
	 * <p>
	 * The parse will try each parse pattern in turn. A parse is only deemed
	 * successful if it parses the whole of the input string. If no parse
	 * patterns match, a ParseException is thrown.
	 * </p>
	 * The parser will be lenient toward the parsed date.
	 * 
	 * @param str
	 *            the date to parse, not null
	 * @param parsePatterns
	 *            the date format patterns to use, see SimpleDateFormat, not
	 *            null
	 * @return the parsed date
	 * @throws IllegalArgumentException
	 *             if the date string or pattern array is null
	 * @throws ParseException
	 *             if none of the date patterns were suitable (or there were
	 *             none)
	 */
	public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
		return parseDateWithLeniency(str, parsePatterns, true);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Parses a string representing a date by trying a variety of different
	 * parsers.
	 * </p>
	 * 
	 * <p>
	 * The parse will try each parse pattern in turn. A parse is only deemed
	 * successful if it parses the whole of the input string. If no parse
	 * patterns match, a ParseException is thrown.
	 * </p>
	 * The parser parses strictly - it does not allow for dates such as
	 * "February 942, 1996".
	 * 
	 * @param str
	 *            the date to parse, not null
	 * @param parsePatterns
	 *            the date format patterns to use, see SimpleDateFormat, not
	 *            null
	 * @return the parsed date
	 * @throws IllegalArgumentException
	 *             if the date string or pattern array is null
	 * @throws ParseException
	 *             if none of the date patterns were suitable
	 * @since 2.5
	 */
	public static Date parseDateStrictly(String str, String[] parsePatterns) throws ParseException {
		return parseDateWithLeniency(str, parsePatterns, false);
	}

	/**
	 * <p>
	 * Parses a string representing a date by trying a variety of different
	 * parsers.
	 * </p>
	 * 
	 * <p>
	 * The parse will try each parse pattern in turn. A parse is only deemed
	 * successful if it parses the whole of the input string. If no parse
	 * patterns match, a ParseException is thrown.
	 * </p>
	 * 
	 * @param str
	 *            the date to parse, not null
	 * @param parsePatterns
	 *            the date format patterns to use, see SimpleDateFormat, not
	 *            null
	 * @param lenient
	 *            Specify whether or not date/time parsing is to be lenient.
	 * @return the parsed date
	 * @throws IllegalArgumentException
	 *             if the date string or pattern array is null
	 * @throws ParseException
	 *             if none of the date patterns were suitable
	 * @see java.util.Calender#isLenient()
	 */
	private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient) throws ParseException {
		if (str == null || parsePatterns == null) {
			throw new IllegalArgumentException("Date and Patterns must not be null");
		}

		SimpleDateFormat parser = new SimpleDateFormat();
		parser.setLenient(lenient);
		ParsePosition pos = new ParsePosition(0);
		for (int i = 0; i < parsePatterns.length; i++) {

			String pattern = parsePatterns[i];

			// LANG-530 - need to make sure 'ZZ' output doesn't get passed to
			// SimpleDateFormat
			if (parsePatterns[i].endsWith("ZZ")) {
				pattern = pattern.substring(0, pattern.length() - 1);
			}

			parser.applyPattern(pattern);
			pos.setIndex(0);

			String str2 = str;
			// LANG-530 - need to make sure 'ZZ' output doesn't hit
			// SimpleDateFormat as it will ParseException
			if (parsePatterns[i].endsWith("ZZ")) {
				int signIdx = indexOfSignChars(str2, 0);
				while (signIdx >= 0) {
					str2 = reformatTimezone(str2, signIdx);
					signIdx = indexOfSignChars(str2, ++signIdx);
				}
			}

			Date date = parser.parse(str2, pos);
			if (date != null && pos.getIndex() == str2.length()) {
				return date;
			}
		}
		throw new ParseException("Unable to parse the date: " + str, -1);
	}

	/**
	 * Index of sign charaters (i.e. '+' or '-').
	 * 
	 * @param str
	 *            The string to search
	 * @param startPos
	 *            The start position
	 * @return the index of the first sign character or -1 if not found
	 */
	private static int indexOfSignChars(String str, int startPos) {
		int idx = StringUtils.indexOf(str, '+', startPos);
		if (idx < 0) {
			idx = StringUtils.indexOf(str, '-', startPos);
		}
		return idx;
	}

	/**
	 * Reformat the timezone in a date string.
	 * 
	 * @param str
	 *            The input string
	 * @param signIdx
	 *            The index position of the sign characters
	 * @return The reformatted string
	 */
	private static String reformatTimezone(String str, int signIdx) {
		String str2 = str;
		if (signIdx >= 0 && signIdx + 5 < str.length() && Character.isDigit(str.charAt(signIdx + 1)) && Character.isDigit(str.charAt(signIdx + 2)) && str.charAt(signIdx + 3) == ':' && Character.isDigit(str.charAt(signIdx + 4)) && Character.isDigit(str.charAt(signIdx + 5))) {
			str2 = str.substring(0, signIdx + 3) + str.substring(signIdx + 4);
		}
		return str2;
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of years to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of months to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of weeks to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of days to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of hours to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of minutes to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of seconds to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of milliseconds to a date returning a new object. The
	 * original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds to a date returning a new object. The original date object is
	 * unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to add to
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @deprecated Will become privately scoped in 3.0
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the years field to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setYears(Date date, int amount) {
		return set(date, Calendar.YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the months field to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setMonths(Date date, int amount) {
		return set(date, Calendar.MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the day of month field to a date returning a new object. The
	 * original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setDays(Date date, int amount) {
		return set(date, Calendar.DAY_OF_MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the hours field to a date returning a new object. Hours range from
	 * 0-23. The original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setHours(Date date, int amount) {
		return set(date, Calendar.HOUR_OF_DAY, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the minute field to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setMinutes(Date date, int amount) {
		return set(date, Calendar.MINUTE, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the seconds field to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setSeconds(Date date, int amount) {
		return set(date, Calendar.SECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the miliseconds field to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	public static Date setMilliseconds(Date date, int amount) {
		return set(date, Calendar.MILLISECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Sets the specified field to a date returning a new object. This does not
	 * use a lenient calendar. The original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to set the amount to
	 * @param amount
	 *            the amount to set
	 * @return a new Date object set with the specified value
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @since 2.4
	 */
	private static Date set(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		// getInstance() returns a new object, so this method is thread safe.
		Calendar c = Calendar.getInstance();
		c.setLenient(false);
		c.setTime(date);
		c.set(calendarField, amount);
		return c.getTime();
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Round this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if this
	 * was passed with HOUR, it would return 28 Mar 2002 14:00:00.000. If this
	 * was passed with MONTH, it would return 1 April 2002 0:00:00.000.
	 * </p>
	 * 
	 * <p>
	 * For a date in a timezone that handles the change to daylight saving time,
	 * rounding to Calendar.HOUR_OF_DAY will behave as follows. Suppose daylight
	 * saving time begins at 02:00 on March 30. Rounding a date that crosses
	 * this time would produce the following values:
	 * <ul>
	 * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
	 * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Date round(Date date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar gval = Calendar.getInstance();
		gval.setTime(date);
		modify(gval, field, MODIFY_ROUND);
		return gval.getTime();
	}

	/**
	 * <p>
	 * Round this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if this
	 * was passed with HOUR, it would return 28 Mar 2002 14:00:00.000. If this
	 * was passed with MONTH, it would return 1 April 2002 0:00:00.000.
	 * </p>
	 * 
	 * <p>
	 * For a date in a timezone that handles the change to daylight saving time,
	 * rounding to Calendar.HOUR_OF_DAY will behave as follows. Suppose daylight
	 * saving time begins at 02:00 on March 30. Rounding a date that crosses
	 * this time would produce the following values:
	 * <ul>
	 * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
	 * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date (a different object)
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Calendar round(Calendar date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar rounded = (Calendar) date.clone();
		modify(rounded, field, MODIFY_ROUND);
		return rounded;
	}

	/**
	 * <p>
	 * Round this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if this
	 * was passed with HOUR, it would return 28 Mar 2002 14:00:00.000. If this
	 * was passed with MONTH, it would return 1 April 2002 0:00:00.000.
	 * </p>
	 * 
	 * <p>
	 * For a date in a timezone that handles the change to daylight saving time,
	 * rounding to Calendar.HOUR_OF_DAY will behave as follows. Suppose daylight
	 * saving time begins at 02:00 on March 30. Rounding a date that crosses
	 * this time would produce the following values:
	 * <ul>
	 * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
	 * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
	 * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, either Date or Calendar
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ClassCastException
	 *             if the object type is not a <code>Date</code> or
	 *             <code>Calendar</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Date round(Object date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		if (date instanceof Date) {
			return round((Date) date, field);
		} else if (date instanceof Calendar) {
			return round((Calendar) date, field).getTime();
		} else {
			throw new ClassCastException("Could not round " + date);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Truncate this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Date truncate(Date date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar gval = Calendar.getInstance();
		gval.setTime(date);
		modify(gval, field, MODIFY_TRUNCATE);
		return gval.getTime();
	}

	/**
	 * <p>
	 * Truncate this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date (a different object)
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Calendar truncate(Calendar date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar truncated = (Calendar) date.clone();
		modify(truncated, field, MODIFY_TRUNCATE);
		return truncated;
	}

	/**
	 * <p>
	 * Truncate this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, either <code>Date</code> or
	 *            <code>Calendar</code>
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ClassCastException
	 *             if the object type is not a <code>Date</code> or
	 *             <code>Calendar</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	public static Date truncate(Object date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		if (date instanceof Date) {
			return truncate((Date) date, field);
		} else if (date instanceof Calendar) {
			return truncate((Calendar) date, field).getTime();
		} else {
			throw new ClassCastException("Could not truncate " + date);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Ceil this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 * @since 2.5
	 */
	public static Date ceiling(Date date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar gval = Calendar.getInstance();
		gval.setTime(date);
		modify(gval, field, MODIFY_CEILING);
		return gval.getTime();
	}

	/**
	 * <p>
	 * Ceil this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date (a different object)
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 * @since 2.5
	 */
	public static Calendar ceiling(Calendar date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar ceiled = (Calendar) date.clone();
		modify(ceiled, field, MODIFY_CEILING);
		return ceiled;
	}

	/**
	 * <p>
	 * Ceil this date, leaving the field specified as the most significant
	 * field.
	 * </p>
	 * 
	 * <p>
	 * For example, if you had the datetime of 28 Mar 2002 13:45:01.231, if you
	 * passed with HOUR, it would return 28 Mar 2002 13:00:00.000. If this was
	 * passed with MONTH, it would return 1 Mar 2002 0:00:00.000.
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, either <code>Date</code> or
	 *            <code>Calendar</code>
	 * @param field
	 *            the field from <code>Calendar</code> or
	 *            <code>SEMI_MONTH</code>
	 * @return the rounded date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ClassCastException
	 *             if the object type is not a <code>Date</code> or
	 *             <code>Calendar</code>
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 * @since 2.5
	 */
	public static Date ceiling(Object date, int field) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		if (date instanceof Date) {
			return ceiling((Date) date, field);
		} else if (date instanceof Calendar) {
			return ceiling((Calendar) date, field).getTime();
		} else {
			throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Internal calculation method.
	 * </p>
	 * 
	 * @param val
	 *            the calendar
	 * @param field
	 *            the field constant
	 * @param modType
	 *            type to truncate, round or ceiling
	 * @throws ArithmeticException
	 *             if the year is over 280 million
	 */
	private static void modify(Calendar val, int field, int modType) {
		if (val.get(Calendar.YEAR) > 280000000) {
			throw new ArithmeticException("Calendar value too large for accurate calculations");
		}

		if (field == Calendar.MILLISECOND) {
			return;
		}

		// ----------------- Fix for LANG-59 ---------------------- START
		// ---------------
		// see http://issues.apache.org/jira/browse/LANG-59
		//
		// Manually truncate milliseconds, seconds and minutes, rather than
		// using
		// Calendar methods.

		Date date = val.getTime();
		long time = date.getTime();
		boolean done = false;

		// truncate milliseconds
		int millisecs = val.get(Calendar.MILLISECOND);
		if (MODIFY_TRUNCATE == modType || millisecs < 500) {
			time = time - millisecs;
		}
		if (field == Calendar.SECOND) {
			done = true;
		}

		// truncate seconds
		int seconds = val.get(Calendar.SECOND);
		if (!done && (MODIFY_TRUNCATE == modType || seconds < 30)) {
			time = time - (seconds * 1000L);
		}
		if (field == Calendar.MINUTE) {
			done = true;
		}

		// truncate minutes
		int minutes = val.get(Calendar.MINUTE);
		if (!done && (MODIFY_TRUNCATE == modType || minutes < 30)) {
			time = time - (minutes * 60000L);
		}

		// reset time
		if (date.getTime() != time) {
			date.setTime(time);
			val.setTime(date);
		}
		// ----------------- Fix for LANG-59 ----------------------- END
		// ----------------

		boolean roundUp = false;
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				if (fields[i][j] == field) {
					// This is our field... we stop looping
					if (modType == MODIFY_CEILING || (modType == MODIFY_ROUND && roundUp)) {
						if (field == DateUtils.SEMI_MONTH) {
							// This is a special case that's hard to generalize
							// If the date is 1, we round up to 16, otherwise
							// we subtract 15 days and add 1 month
							if (val.get(Calendar.DATE) == 1) {
								val.add(Calendar.DATE, 15);
							} else {
								val.add(Calendar.DATE, -15);
								val.add(Calendar.MONTH, 1);
							}
							// ----------------- Fix for LANG-440
							// ---------------------- START ---------------
						} else if (field == Calendar.AM_PM) {
							// This is a special case
							// If the time is 0, we round up to 12, otherwise
							// we subtract 12 hours and add 1 day
							if (val.get(Calendar.HOUR_OF_DAY) == 0) {
								val.add(Calendar.HOUR_OF_DAY, 12);
							} else {
								val.add(Calendar.HOUR_OF_DAY, -12);
								val.add(Calendar.DATE, 1);
							}
							// ----------------- Fix for LANG-440
							// ---------------------- END ---------------
						} else {
							// We need at add one to this field since the
							// last number causes us to round up
							val.add(fields[i][0], 1);
						}
					}
					return;
				}
			}
			// We have various fields that are not easy roundings
			int offset = 0;
			boolean offsetSet = false;
			// These are special types of fields that require different rounding
			// rules
			switch (field) {
			case DateUtils.SEMI_MONTH:
				if (fields[i][0] == Calendar.DATE) {
					// If we're going to drop the DATE field's value,
					// we want to do this our own way.
					// We need to subtrace 1 since the date has a minimum of 1
					offset = val.get(Calendar.DATE) - 1;
					// If we're above 15 days adjustment, that means we're in
					// the
					// bottom half of the month and should stay accordingly.
					if (offset >= 15) {
						offset -= 15;
					}
					// Record whether we're in the top or bottom half of that
					// range
					roundUp = offset > 7;
					offsetSet = true;
				}
				break;
			case Calendar.AM_PM:
				if (fields[i][0] == Calendar.HOUR_OF_DAY) {
					// If we're going to drop the HOUR field's value,
					// we want to do this our own way.
					offset = val.get(Calendar.HOUR_OF_DAY);
					if (offset >= 12) {
						offset -= 12;
					}
					roundUp = offset >= 6;
					offsetSet = true;
				}
				break;
			}
			if (!offsetSet) {
				int min = val.getActualMinimum(fields[i][0]);
				int max = val.getActualMaximum(fields[i][0]);
				// Calculate the offset from the minimum allowed value
				offset = val.get(fields[i][0]) - min;
				// Set roundUp if this is more than half way between the minimum
				// and maximum
				roundUp = offset > ((max - min) / 2);
			}
			// We need to remove this field
			if (offset != 0) {
				val.set(fields[i][0], val.get(fields[i][0]) - offset);
			}
		}
		throw new IllegalArgumentException("The field " + field + " is not supported");

	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * This constructs an <code>Iterator</code> over each day in a date range
	 * defined by a focus date and range style.
	 * </p>
	 * 
	 * <p>
	 * For instance, passing Thursday, July 4, 2002 and a
	 * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code> that
	 * starts with Sunday, June 30, 2002 and ends with Saturday, August 3, 2002,
	 * returning a Calendar instance for each intermediate day.
	 * </p>
	 * 
	 * <p>
	 * This method provides an iterator that returns Calendar objects. The days
	 * are progressed using {@link Calendar#add(int, int)}.
	 * </p>
	 * 
	 * @param focus
	 *            the date to work with, not null
	 * @param rangeStyle
	 *            the style constant to use. Must be one of
	 *            {@link DateUtils#RANGE_MONTH_SUNDAY},
	 *            {@link DateUtils#RANGE_MONTH_MONDAY},
	 *            {@link DateUtils#RANGE_WEEK_SUNDAY},
	 *            {@link DateUtils#RANGE_WEEK_MONDAY},
	 *            {@link DateUtils#RANGE_WEEK_RELATIVE},
	 *            {@link DateUtils#RANGE_WEEK_CENTER}
	 * @return the date iterator, which always returns Calendar instances
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the rangeStyle is invalid
	 */
	public static Iterator iterator(Date focus, int rangeStyle) {
		if (focus == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar gval = Calendar.getInstance();
		gval.setTime(focus);
		return iterator(gval, rangeStyle);
	}

	/**
	 * <p>
	 * This constructs an <code>Iterator</code> over each day in a date range
	 * defined by a focus date and range style.
	 * </p>
	 * 
	 * <p>
	 * For instance, passing Thursday, July 4, 2002 and a
	 * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code> that
	 * starts with Sunday, June 30, 2002 and ends with Saturday, August 3, 2002,
	 * returning a Calendar instance for each intermediate day.
	 * </p>
	 * 
	 * <p>
	 * This method provides an iterator that returns Calendar objects. The days
	 * are progressed using {@link Calendar#add(int, int)}.
	 * </p>
	 * 
	 * @param focus
	 *            the date to work with
	 * @param rangeStyle
	 *            the style constant to use. Must be one of
	 *            {@link DateUtils#RANGE_MONTH_SUNDAY},
	 *            {@link DateUtils#RANGE_MONTH_MONDAY},
	 *            {@link DateUtils#RANGE_WEEK_SUNDAY},
	 *            {@link DateUtils#RANGE_WEEK_MONDAY},
	 *            {@link DateUtils#RANGE_WEEK_RELATIVE},
	 *            {@link DateUtils#RANGE_WEEK_CENTER}
	 * @return the date iterator
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the rangeStyle is invalid
	 */
	public static Iterator iterator(Calendar focus, int rangeStyle) {
		if (focus == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar start = null;
		Calendar end = null;
		int startCutoff = Calendar.SUNDAY;
		int endCutoff = Calendar.SATURDAY;
		switch (rangeStyle) {
		case RANGE_MONTH_SUNDAY:
		case RANGE_MONTH_MONDAY:
			// Set start to the first of the month
			start = truncate(focus, Calendar.MONTH);
			// Set end to the last of the month
			end = (Calendar) start.clone();
			end.add(Calendar.MONTH, 1);
			end.add(Calendar.DATE, -1);
			// Loop start back to the previous sunday or monday
			if (rangeStyle == RANGE_MONTH_MONDAY) {
				startCutoff = Calendar.MONDAY;
				endCutoff = Calendar.SUNDAY;
			}
			break;
		case RANGE_WEEK_SUNDAY:
		case RANGE_WEEK_MONDAY:
		case RANGE_WEEK_RELATIVE:
		case RANGE_WEEK_CENTER:
			// Set start and end to the current date
			start = truncate(focus, Calendar.DATE);
			end = truncate(focus, Calendar.DATE);
			switch (rangeStyle) {
			case RANGE_WEEK_SUNDAY:
				// already set by default
				break;
			case RANGE_WEEK_MONDAY:
				startCutoff = Calendar.MONDAY;
				endCutoff = Calendar.SUNDAY;
				break;
			case RANGE_WEEK_RELATIVE:
				startCutoff = focus.get(Calendar.DAY_OF_WEEK);
				endCutoff = startCutoff - 1;
				break;
			case RANGE_WEEK_CENTER:
				startCutoff = focus.get(Calendar.DAY_OF_WEEK) - 3;
				endCutoff = focus.get(Calendar.DAY_OF_WEEK) + 3;
				break;
			}
			break;
		default:
			throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
		}
		if (startCutoff < Calendar.SUNDAY) {
			startCutoff += 7;
		}
		if (startCutoff > Calendar.SATURDAY) {
			startCutoff -= 7;
		}
		if (endCutoff < Calendar.SUNDAY) {
			endCutoff += 7;
		}
		if (endCutoff > Calendar.SATURDAY) {
			endCutoff -= 7;
		}
		while (start.get(Calendar.DAY_OF_WEEK) != startCutoff) {
			start.add(Calendar.DATE, -1);
		}
		while (end.get(Calendar.DAY_OF_WEEK) != endCutoff) {
			end.add(Calendar.DATE, 1);
		}
		return new DateIterator(start, end);
	}

	/**
	 * <p>
	 * This constructs an <code>Iterator</code> over each day in a date range
	 * defined by a focus date and range style.
	 * </p>
	 * 
	 * <p>
	 * For instance, passing Thursday, July 4, 2002 and a
	 * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code> that
	 * starts with Sunday, June 30, 2002 and ends with Saturday, August 3, 2002,
	 * returning a Calendar instance for each intermediate day.
	 * </p>
	 * 
	 * @param focus
	 *            the date to work with, either <code>Date</code> or
	 *            <code>Calendar</code>
	 * @param rangeStyle
	 *            the style constant to use. Must be one of the range styles
	 *            listed for the {@link #iterator(Calendar, int)} method.
	 * @return the date iterator
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code>
	 * @throws ClassCastException
	 *             if the object type is not a <code>Date</code> or
	 *             <code>Calendar</code>
	 */
	public static Iterator iterator(Object focus, int rangeStyle) {
		if (focus == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		if (focus instanceof Date) {
			return iterator((Date) focus, rangeStyle);
		} else if (focus instanceof Calendar) {
			return iterator((Calendar) focus, rangeStyle);
		} else {
			throw new ClassCastException("Could not iterate based on " + focus);
		}
	}

	/**
	 * <p>
	 * Returns the number of milliseconds within the fragment. All datefields
	 * greater than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the milliseconds of any date will only return the number of
	 * milliseconds of the current second (resulting in a number between 0 and
	 * 999). This method will retrieve the number of milliseconds for any
	 * fragment. For example, if you want to calculate the number of
	 * milliseconds past today, your fragment is Calendar.DATE or
	 * Calendar.DAY_OF_YEAR. The result will be all milliseconds of the past
	 * hour(s), minutes(s) and second(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a SECOND field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.SECOND as fragment will
	 * return 538</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.SECOND as fragment will
	 * return 538</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10538 (10*1000 + 538)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in milliseconds)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @return number of milliseconds within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInMilliseconds(Date date, int fragment) {
		return getFragment(date, fragment, Calendar.MILLISECOND);
	}

	/**
	 * <p>
	 * Returns the number of seconds within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the seconds of any date will only return the number of seconds of
	 * the current minute (resulting in a number between 0 and 59). This method
	 * will retrieve the number of seconds for any fragment. For example, if you
	 * want to calculate the number of seconds past today, your fragment is
	 * Calendar.DATE or Calendar.DAY_OF_YEAR. The result will be all seconds of
	 * the past hour(s) and minutes(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a SECOND field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10 (equivalent to deprecated date.getSeconds())</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10 (equivalent to deprecated date.getSeconds())</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 26110 (7*3600 + 15*60 + 10)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in seconds)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @return number of seconds within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInSeconds(Date date, int fragment) {
		return getFragment(date, fragment, Calendar.SECOND);
	}

	/**
	 * <p>
	 * Returns the number of minutes within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the minutes of any date will only return the number of minutes of
	 * the current hour (resulting in a number between 0 and 59). This method
	 * will retrieve the number of minutes for any fragment. For example, if you
	 * want to calculate the number of minutes past this month, your fragment is
	 * Calendar.MONTH. The result will be all minutes of the past day(s) and
	 * hour(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a MINUTE field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment
	 * will return 15 (equivalent to deprecated date.getMinutes())</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment
	 * will return 15 (equivalent to deprecated date.getMinutes())</li>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 15</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 435 (7*60 + 15)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in minutes)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @return number of minutes within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInMinutes(Date date, int fragment) {
		return getFragment(date, fragment, Calendar.MINUTE);
	}

	/**
	 * <p>
	 * Returns the number of hours within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the hours of any date will only return the number of hours of the
	 * current day (resulting in a number between 0 and 23). This method will
	 * retrieve the number of hours for any fragment. For example, if you want
	 * to calculate the number of hours past this month, your fragment is
	 * Calendar.MONTH. The result will be all hours of the past day(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a HOUR field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 7 (equivalent to deprecated date.getHours())</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 7 (equivalent to deprecated date.getHours())</li>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 7</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 127 (5*24 + 7)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in hours)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @return number of hours within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInHours(Date date, int fragment) {
		return getFragment(date, fragment, Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>
	 * Returns the number of days within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the days of any date will only return the number of days of the
	 * current month (resulting in a number between 1 and 31). This method will
	 * retrieve the number of days for any fragment. For example, if you want to
	 * calculate the number of days past this year, your fragment is
	 * Calendar.YEAR. The result will be all days of the past month(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a DAY field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 28, 2008 with Calendar.MONTH as fragment will return 28
	 * (equivalent to deprecated date.getDay())</li>
	 * <li>February 28, 2008 with Calendar.MONTH as fragment will return 28
	 * (equivalent to deprecated date.getDay())</li>
	 * <li>January 28, 2008 with Calendar.YEAR as fragment will return 28</li>
	 * <li>February 28, 2008 with Calendar.YEAR as fragment will return 59</li>
	 * <li>January 28, 2008 with Calendar.MILLISECOND as fragment will return 0
	 * (a millisecond cannot be split in days)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @return number of days within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInDays(Date date, int fragment) {
		return getFragment(date, fragment, Calendar.DAY_OF_YEAR);
	}

	/**
	 * <p>
	 * Returns the number of milliseconds within the fragment. All datefields
	 * greater than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the milliseconds of any date will only return the number of
	 * milliseconds of the current second (resulting in a number between 0 and
	 * 999). This method will retrieve the number of milliseconds for any
	 * fragment. For example, if you want to calculate the number of seconds
	 * past today, your fragment is Calendar.DATE or Calendar.DAY_OF_YEAR. The
	 * result will be all seconds of the past hour(s), minutes(s) and second(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a MILLISECOND field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.SECOND as fragment will
	 * return 538 (equivalent to calendar.get(Calendar.MILLISECOND))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.SECOND as fragment will
	 * return 538 (equivalent to calendar.get(Calendar.MILLISECOND))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10538 (10*1000 + 538)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in milliseconds)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @return number of milliseconds within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
		return getFragment(calendar, fragment, Calendar.MILLISECOND);
	}

	/**
	 * <p>
	 * Returns the number of seconds within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the seconds of any date will only return the number of seconds of
	 * the current minute (resulting in a number between 0 and 59). This method
	 * will retrieve the number of seconds for any fragment. For example, if you
	 * want to calculate the number of seconds past today, your fragment is
	 * Calendar.DATE or Calendar.DAY_OF_YEAR. The result will be all seconds of
	 * the past hour(s) and minutes(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a SECOND field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10 (equivalent to calendar.get(Calendar.SECOND))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will
	 * return 10 (equivalent to calendar.get(Calendar.SECOND))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 26110 (7*3600 + 15*60 + 10)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in seconds)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @return number of seconds within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInSeconds(Calendar calendar, int fragment) {
		return getFragment(calendar, fragment, Calendar.SECOND);
	}

	/**
	 * <p>
	 * Returns the number of minutes within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the minutes of any date will only return the number of minutes of
	 * the current hour (resulting in a number between 0 and 59). This method
	 * will retrieve the number of minutes for any fragment. For example, if you
	 * want to calculate the number of minutes past this month, your fragment is
	 * Calendar.MONTH. The result will be all minutes of the past day(s) and
	 * hour(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a MINUTE field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment
	 * will return 15 (equivalent to calendar.get(Calendar.MINUTES))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment
	 * will return 15 (equivalent to calendar.get(Calendar.MINUTES))</li>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 15</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 435 (7*60 + 15)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in minutes)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @return number of minutes within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInMinutes(Calendar calendar, int fragment) {
		return getFragment(calendar, fragment, Calendar.MINUTE);
	}

	/**
	 * <p>
	 * Returns the number of hours within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the hours of any date will only return the number of hours of the
	 * current day (resulting in a number between 0 and 23). This method will
	 * retrieve the number of hours for any fragment. For example, if you want
	 * to calculate the number of hours past this month, your fragment is
	 * Calendar.MONTH. The result will be all hours of the past day(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a HOUR field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 7 (equivalent to calendar.get(Calendar.HOUR_OF_DAY))</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment
	 * will return 7 (equivalent to calendar.get(Calendar.HOUR_OF_DAY))</li>
	 * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 7</li>
	 * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will
	 * return 127 (5*24 + 7)</li>
	 * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment
	 * will return 0 (a millisecond cannot be split in hours)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @return number of hours within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInHours(Calendar calendar, int fragment) {
		return getFragment(calendar, fragment, Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>
	 * Returns the number of days within the fragment. All datefields greater
	 * than the fragment will be ignored.
	 * </p>
	 * 
	 * <p>
	 * Asking the days of any date will only return the number of days of the
	 * current month (resulting in a number between 1 and 31). This method will
	 * retrieve the number of days for any fragment. For example, if you want to
	 * calculate the number of days past this year, your fragment is
	 * Calendar.YEAR. The result will be all days of the past month(s).
	 * </p>
	 * 
	 * <p>
	 * Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
	 * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
	 * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND A fragment less
	 * than or equal to a DAY field will return 0.
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * <li>January 28, 2008 with Calendar.MONTH as fragment will return 28
	 * (equivalent to calendar.get(Calendar.DAY_OF_MONTH))</li>
	 * <li>February 28, 2008 with Calendar.MONTH as fragment will return 28
	 * (equivalent to calendar.get(Calendar.DAY_OF_MONTH))</li>
	 * <li>January 28, 2008 with Calendar.YEAR as fragment will return 28
	 * (equivalent to calendar.get(Calendar.DAY_OF_YEAR))</li>
	 * <li>February 28, 2008 with Calendar.YEAR as fragment will return 59
	 * (equivalent to calendar.get(Calendar.DAY_OF_YEAR))</li>
	 * <li>January 28, 2008 with Calendar.MILLISECOND as fragment will return 0
	 * (a millisecond cannot be split in days)</li>
	 * </ul>
	 * </p>
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @return number of days within the fragment of date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	public static long getFragmentInDays(Calendar calendar, int fragment) {
		return getFragment(calendar, fragment, Calendar.DAY_OF_YEAR);
	}

	/**
	 * Date-version for fragment-calculation in any unit
	 * 
	 * @param date
	 *            the date to work with, not null
	 * @param fragment
	 *            the Calendar field part of date to calculate
	 * @param unit
	 *            Calendar field defining the unit
	 * @return number of units within the fragment of the date
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	private static long getFragment(Date date, int fragment, int unit) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFragment(calendar, fragment, unit);
	}

	/**
	 * Calendar-version for fragment-calculation in any unit
	 * 
	 * @param calendar
	 *            the calendar to work with, not null
	 * @param fragment
	 *            the Calendar field part of calendar to calculate
	 * @param unit
	 *            Calendar field defining the unit
	 * @return number of units within the fragment of the calendar
	 * @throws IllegalArgumentException
	 *             if the date is <code>null</code> or fragment is not supported
	 * @since 2.4
	 */
	private static long getFragment(Calendar calendar, int fragment, int unit) {
		if (calendar == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		long millisPerUnit = getMillisPerUnit(unit);
		long result = 0;

		// Fragments bigger than a day require a breakdown to days
		switch (fragment) {
		case Calendar.YEAR:
			result += (calendar.get(Calendar.DAY_OF_YEAR) * MILLIS_PER_DAY) / millisPerUnit;
			break;
		case Calendar.MONTH:
			result += (calendar.get(Calendar.DAY_OF_MONTH) * MILLIS_PER_DAY) / millisPerUnit;
			break;
		}

		switch (fragment) {
		// Number of days already calculated for these cases
		case Calendar.YEAR:
		case Calendar.MONTH:

			// The rest of the valid cases
		case Calendar.DAY_OF_YEAR:
		case Calendar.DATE:
			result += (calendar.get(Calendar.HOUR_OF_DAY) * MILLIS_PER_HOUR) / millisPerUnit;
			//$FALL-THROUGH$
		case Calendar.HOUR_OF_DAY:
			result += (calendar.get(Calendar.MINUTE) * MILLIS_PER_MINUTE) / millisPerUnit;
			//$FALL-THROUGH$
		case Calendar.MINUTE:
			result += (calendar.get(Calendar.SECOND) * MILLIS_PER_SECOND) / millisPerUnit;
			//$FALL-THROUGH$
		case Calendar.SECOND:
			result += (calendar.get(Calendar.MILLISECOND) * 1) / millisPerUnit;
			break;
		case Calendar.MILLISECOND:
			break;// never useful
		default:
			throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
		}
		return result;
	}

	/**
	 * Returns the number of millis of a datefield, if this is a constant value
	 * 
	 * @param unit
	 *            A Calendar field which is a valid unit for a fragment
	 * @return number of millis
	 * @throws IllegalArgumentException
	 *             if date can't be represented in millisenconds
	 * @since 2.4
	 */
	private static long getMillisPerUnit(int unit) {
		long result = Long.MAX_VALUE;
		switch (unit) {
		case Calendar.DAY_OF_YEAR:
		case Calendar.DATE:
			result = MILLIS_PER_DAY;
			break;
		case Calendar.HOUR_OF_DAY:
			result = MILLIS_PER_HOUR;
			break;
		case Calendar.MINUTE:
			result = MILLIS_PER_MINUTE;
			break;
		case Calendar.SECOND:
			result = MILLIS_PER_SECOND;
			break;
		case Calendar.MILLISECOND:
			result = 1;
			break;
		default:
			throw new IllegalArgumentException("The unit " + unit + " cannot be represented is milleseconds");
		}
		return result;
	}

	/**
	 * <p>
	 * Date iterator.
	 * </p>
	 */
	static class DateIterator implements Iterator {
		private final Calendar endFinal;
		private final Calendar spot;

		/**
		 * Constructs a DateIterator that ranges from one date to another.
		 * 
		 * @param startFinal
		 *            start date (inclusive)
		 * @param endFinal
		 *            end date (not inclusive)
		 */
		DateIterator(Calendar startFinal, Calendar endFinal) {
			super();
			this.endFinal = endFinal;
			spot = startFinal;
			spot.add(Calendar.DATE, -1);
		}

		/**
		 * Has the iterator not reached the end date yet?
		 * 
		 * @return <code>true</code> if the iterator has yet to reach the end
		 *         date
		 */
		public boolean hasNext() {
			return spot.before(endFinal);
		}

		/**
		 * Return the next calendar in the iteration
		 * 
		 * @return Object calendar for the next date
		 */
		public Object next() {
			if (spot.equals(endFinal)) {
				throw new NoSuchElementException();
			}
			spot.add(Calendar.DATE, 1);
			return spot.clone();
		}

		/**
		 * Always throws UnsupportedOperationException.
		 * 
		 * @throws UnsupportedOperationException
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * 指定日期格式
	 * 
	 * @param formatStr
	 * @return
	 */
	private static DateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATETIME_STR)) {
			return dateFormat;
		} else if (formatStr.equalsIgnoreCase(DATETIME_STR)) {
			return dateTimeFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATEFORMAT_STR)) {
			return zhcnDateFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATETIME_STR)) {
			return zhcnDateTimeFormat;
		} else {
			return new SimpleDateFormat(formatStr);
		}
	}

	/**
	 * 按照默认显示日期时间的格式"yyyy-MM-dd HH:mm:ss"，转化dateTimeStr为Date类型
	 * dateTimeStr必须是"yyyy-MM-dd HH:mm:ss"的形式
	 * 
	 * @param dateTimeStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr) {
		return getDate(dateTimeStr, DATETIME_STR);
	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}

			DateFormat sdf = getDateFormat(formatStr);
			java.util.Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将YYYYMMDD转换成Date日期
	 * 
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public static Date transferDate(String date) throws Exception {
		if (date == null || date.length() < 1)
			return null;

		if (date.length() != 8)
			throw new Exception("日期格式错误");
		String con = "-";

		String yyyy = date.substring(0, 4);
		String mm = date.substring(4, 6);
		String dd = date.substring(6, 8);

		int month = Integer.parseInt(mm);
		int day = Integer.parseInt(dd);
		if (month < 1 || month > 12 || day < 1 || day > 31)
			throw new Exception("日期格式错误");

		String str = yyyy + con + mm + con + dd;
		return getDate(str, DATEFORMAT_STR);
	}

	/**
	 * 将YYYY－MM－DD日期转换成yyyymmdd格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDDDate(Date date) {
		if (date == null)
			return null;
		String yyyy = getYear(date) + "";
		String mm = getMonth(date) + "";
		String dd = getDayInMonth(date) + "";
		return yyyy + mm + dd;
	}

	/**
	 * 将YYYY－MM－DD日期转换成YYYYMMDDHHMMSS格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDDHHMMSSDate(Date date) {
		if (date == null)
			return null;
		String yyyy = getYear(date) + "";
		String mm = getMonth(date) + "";
		String dd = getDayInMonth(date) + "";
		String hh = getHour(date) + "";
		String min = getMin(date) + "";
		String ss = getSecond(date) + "";

		return yyyy + mm + dd + hh + min + ss;
	}

	/***
	 * 格式化日期字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String formatStr) {
		if (date != null && StringUtils.isNotBlank(formatStr)) {
			DateFormat dateFormat = new SimpleDateFormat(formatStr);
			return dateFormat.format(date);
		}

		return "";

	}

	/**
	 * 将YYYY－MM－DD日期转换成yyyymmdd格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDDDate(String date) {
		return getYYYYMMDDDate(getDate(date, DATEFORMAT_STR));
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATETIME_STR);
	}

	/**
	 * 返回一个yyyy-MM-dd HH:mm:ss 形式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getTimeString(String dateTime) {
		return getTimeString(dateTime, DATETIME_STR);
	}

	/**
	 * 返回一个formatStr格式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @param formatStr
	 * @return
	 */
	public static String getTimeString(String dateTime, String formatStr) {
		Date d = getDate(dateTime, formatStr);
		String s = formatDate(d);
		return s.substring(DATETIME_STR.indexOf('H'));
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return formatDate(Calendar.getInstance().getTime(), DATEFORMAT_STR);
	}

	/**
	 * 获取当前日期yyyy年MM月dd日的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDate() {
		return formatDate(new Date(), ZHCN_DATEFORMAT_STR);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		return formatDate(new Date(), DATETIME_STR);
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDateTime() {
		return formatDate(new Date(), ZHCN_DATETIME_STR);
	}

	/**
	 * 获取日期d的days天后的一个Date
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	public static Date getInternalDateByMon(Date d, int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}

	public static Date getInternalDateByYear(Date d, int years) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.YEAR, years);
		return now.getTime();
	}

	public static Date getInternalDateBySec(Date d, int sec) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.SECOND, sec);
		return now.getTime();
	}

	public static Date getInternalDateByMin(Date d, int min) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MINUTE, min);
		return now.getTime();
	}

	public static Date getInternalDateByHour(Date d, int hours) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.HOUR_OF_DAY, hours);
		return now.getTime();
	}

	/**
	 * 根据一个日期字符串，返回日期格式，目前支持4种 如果都不是，则返回null
	 * 
	 * @param DateString
	 * @return
	 */
	public static String getFormateStr(String DateString) {
		String patternStr1 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}"; // "yyyy-MM-dd"
		String patternStr2 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"; // "yyyy-MM-dd HH:mm:ss";
		String patternStr3 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";// "yyyy年MM月dd日"
		String patternStr4 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[0-9]{1,2}时[0-9]{1,2}分[0-9]{1,2}秒";// "yyyy年MM月dd日HH时mm分ss秒"

		Pattern p = Pattern.compile(patternStr1);
		Matcher m = p.matcher(DateString);
		boolean b = m.matches();
		if (b)
			return DATEFORMAT_STR;
		p = Pattern.compile(patternStr2);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATEFORMAT_STR;

		p = Pattern.compile(patternStr3);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATEFORMAT_STR;

		p = Pattern.compile(patternStr4);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATETIME_STR;
		return null;
	}

	/**
	 * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDateTime(String dateStr) {
		Date d = getDate(dateStr);
		return formatDate(d, ZHCN_DATETIME_STR);
	}

	/**
	 * 将一个"yyyy-MM-dd"字符串，转换成"yyyy年MM月dd日"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDate(String dateStr) {
		Date d = getDate(dateStr, DATEFORMAT_STR);
		return formatDate(d, ZHCN_DATEFORMAT_STR);
	}

	/**
	 * 将dateStr从fmtFrom转换到fmtTo的格式
	 * 
	 * @param dateStr
	 * @param fmtFrom
	 * @param fmtTo
	 * @return
	 */
	public static String getDateStr(String dateStr, String fmtFrom, String fmtTo) {
		Date d = getDate(dateStr, fmtFrom);
		return formatDate(d, fmtTo);
	}

	/**
	 * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareDateStr(String time1, String time2) {
		Date d1 = getDate(time1);
		Date d2 = getDate(time2);
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(BigDecimal hours) {
		BigDecimal bd;
		bd = hours.multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取Date中的分钟
	 * 
	 * @param d
	 * @return
	 */
	public static int getMin(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的小时(24小时)
	 * 
	 * @param d
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的秒
	 * 
	 * @param d
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getDayInMonth(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayInYear(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取月份，1-12月
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取19xx,20xx形式的年
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 得到d的上个月的年份+月份,如200505
	 * 
	 * @return
	 */
	public static String getYearMonthOfLastMon(Date date) {
		Date newdate = getInternalDateByMon(date, -1);
		String year = String.valueOf(getYear(newdate));
		String month = String.valueOf(getMonth(newdate));
		return year + month;
	}

	/**
	 * 得到当前日期的年和月如200509
	 * 
	 * @return String
	 */
	public static String getCurYearMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String DATE_FORMAT = "yyyyMM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	public static Date getNextMonth(String year, String month) {
		String dateStr = year + "-" + month + "-01";
		Date date = getDate(dateStr, DATEFORMAT_STR);
		return getInternalDateByMon(date, 1);
	}

	public static Date getLastMonth(String year, String month) {
		String dateStr = year + "-" + month + "-01";
		Date date = getDate(dateStr, DATEFORMAT_STR);
		return getInternalDateByMon(date, -1);
	}

	/**
	 * 得到日期d，按照页面日期控件格式，如"2001-3-16"
	 * 
	 * @param d
	 * @return
	 */
	public static String getSingleNumDate(Date d) {
		return formatDate(d, DATEFORMAT_STR);
	}

	/**
	 * 得到d半年前的日期,"yyyy-MM-dd"
	 * 
	 * @param d
	 * @return
	 */
	public static String getHalfYearBeforeStr(Date d) {
		return formatDate(getInternalDateByMon(d, -6), DATEFORMAT_STR);
	}

	/**
	 * 得到当前日期D的月底的前/后若干天的时间,<0表示之前，>0表示之后
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static String getInternalDateByLastDay(Date d, int days) {

		return formatDate(getInternalDateByDay(d, days), DATEFORMAT_STR);
	}

	/***
	 * 当前日期的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date startTimeDate(String dateStr, String dateFormat) {
		return startTimeDate(DateUtils.getDate(dateStr, dateFormat));
	}

	/***
	 * 当前日期的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date endTimeDate(String dateStr, String dateFormat) {
		return endTimeDate(DateUtils.getDate(dateStr, dateFormat));
	}

	/***
	 * 当前日期的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date startTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();
	}

	/***
	 * 当前日期的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date endTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		return calendar.getTime();
	}

	/**
	 * 日期中的年月日相加
	 * 
	 * @param field
	 *            int 需要加的字段 年 月 日
	 * @param amount
	 *            int 加多少
	 * @return String
	 */
	public static String addDate(int field, int amount) {
		int temp = 0;
		if (field == 1) {
			temp = Calendar.YEAR;
		}
		if (field == 2) {
			temp = Calendar.MONTH;
		}
		if (field == 3) {
			temp = Calendar.DATE;
		}

		String Time = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			cal.add(temp, amount);
			Time = sdf.format(cal.getTime());
			return Time;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得系统当前月份的天数
	 * 
	 * @return
	 */
	public static int getCurentMonthDay() {
		Date date = Calendar.getInstance().getTime();
		return getMonthDay(date);
	}

	/**
	 * 获得指定日期月份的天数
	 * 
	 * @return
	 */
	public static int getMonthDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 获得指定日期月份的天数 yyyy-mm-dd
	 * 
	 * @return
	 */
	public static int getMonthDay(String date) {
		Date strDate = getDate(date, DATEFORMAT_STR);
		return getMonthDay(strDate);

	}

	/***
	 * 获取当前日期的字符串格式 yyyy-MM-dd
	 * 
	 * @param cal
	 * @return
	 */
	public static String getDateStr(Calendar cal) {

		return dateFormat.format(cal.getTime());
	}

	/***
	 * 两个日期之间的天数差距
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long dateDiffInDays(Date startDate, Date endDate) {

		if (startDate == null) {
			return 0;
		}

		if (endDate == null) {
			return 0;
		}

		return (startDate.getTime() - endDate.getTime()) / MILLIS_PER_DAY;
	}

	/***
	 * 两个日期之间秒数差距
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long dateDiffInSecond(Date startDate, Date endDate) {

		if (startDate == null) {
			return 0;
		}

		if (endDate == null) {
			return 0;
		}

		return (startDate.getTime() - endDate.getTime()) / MILLIS_PER_SECOND;
	}

	/***
	 * 两个日期之间秒数差距
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long dateDiffInMinute(Date startDate, Date endDate) {

		if (startDate == null) {
			return 0;
		}

		if (endDate == null) {
			return 0;
		}

		return (startDate.getTime() - endDate.getTime()) / MILLIS_PER_MINUTE;
	}
	
	/**
	 * 获得年龄 yyyy-mm-dd
	 * @param birth
	 * @return
	 */
	public static String getAge(String birth) {
		String today = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
		String[] bStr = birth.split("-");
		String[] tStr = today.split("-");

		int yDif = Integer.parseInt(tStr[0]) - Integer.parseInt(bStr[0]);
		int mDif = Integer.parseInt(tStr[1]) - Integer.parseInt(bStr[1]);
		int dDif = Integer.parseInt(tStr[2]) - Integer.parseInt(bStr[2]);

		if (mDif < 0) {// 不满这岁
			return String.valueOf(yDif - 1);
		} else if (mDif > 0) {// 满了这岁
			return String.valueOf(yDif);
		} else {// 要看day
			if (dDif < 0) {// 不满
				return String.valueOf(yDif - 1);
			} else {// 满了
				return String.valueOf(yDif);
			}
		}
	}
	
	public static boolean isLimitTimeScope(String minTime,String maxTime) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		Date currentDate = new Date();
        int current = currentDate.getHours()*60*60+currentDate.getMinutes()*60+currentDate.getSeconds();
        
        Date minTargetTime = df.parse(minTime.trim());
        int minTarget = minTargetTime.getHours()*60*60+minTargetTime.getMinutes()*60+minTargetTime.getSeconds();
        
        Date maxTargetTime = df.parse(maxTime.trim());
        int maxTarget = maxTargetTime.getHours()*60*60+maxTargetTime.getMinutes()*60+maxTargetTime.getSeconds();
        
        if(maxTarget > minTarget){
        	if(current < maxTarget && current > minTarget){
            	return true;
            }else{
            	return false;
            }
        }else{
        	if(current > maxTarget && current < minTarget){
            	return false;
            }else{
            	return true;
            }
        }
	}
	
	/**
	 * 计算年龄
	 * @param birthDayString
	 * @return
	 * @throws Exception
	 */
	public static int getAgeOfCalendar(String birthDayString) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDay = df.parse(birthDayString);
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow < monthBirth || (monthNow == monthBirth && dayOfMonthNow < dayOfMonthBirth)) {
			age--;
		}
        return age;
    }

	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtils.getDayInYear(new Date()));

	}
}
