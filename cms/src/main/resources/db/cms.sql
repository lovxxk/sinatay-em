DROP TABLE CMS_CHANNEL;
CREATE TABLE CMS_CHANNEL (
	CHANNELID NUMBER(5,0) NOT NULL, 
	SITEID VARCHAR2(500), 
	CHNLNAME VARCHAR2(200), 
	CHNLDESC VARCHAR2(500), 
	PARENTID VARCHAR2(50), 
	CHNLORDER NUMBER(5,0) DEFAULT 0, 
	CHNLDATAPATH VARCHAR2(500), 
	CHNLTYPE VARCHAR2(200), 
	CHNLMAKEDATE DATE, 
	CRUSER VARCHAR2(50), 
	STATUS VARCHAR2(200), 
	LINKURL VARCHAR2(500), 
	CHNLDESNAME VARCHAR2(200), 
	CHNLDATASTATUS NUMBER(5,0), 
	CHANNELPOWER VARCHAR2(10), 
	MIRRORID VARCHAR2(20), 
	CONSTRAINT P_KEY_1 PRIMARY KEY (CHANNELID)
);

comment on column CMS_CHANNEL.CHANNELID is '';
comment on column CMS_CHANNEL.SITEID is '';
comment on column CMS_CHANNEL.CHNLNAME is '';
comment on column CMS_CHANNEL.CHNLDESC is '';
comment on column CMS_CHANNEL.PARENTID is '';
comment on column CMS_CHANNEL.CHNLORDER is '';
comment on column CMS_CHANNEL.CHNLDATAPATH is '';
comment on column CMS_CHANNEL.CHNLTYPE is '';
comment on column CMS_CHANNEL.CHNLMAKEDATE is '';
comment on column CMS_CHANNEL.CRUSER is '';
comment on column CMS_CHANNEL.STATUS is '';
comment on column CMS_CHANNEL.LINKURL is '';
comment on column CMS_CHANNEL.CHNLDESNAME is '';
comment on column CMS_CHANNEL.CHNLDATASTATUS is '';
comment on column CMS_CHANNEL.CHANNELPOWER is '';
comment on column CMS_CHANNEL.MIRRORID is '';


DROP TABLE CMS_CHANNEL_TEMPLET;
CREATE TABLE CMS_CHANNEL_TEMPLET (
	ID NUMBER(5,0) NOT NULL, 
	CHLID VARCHAR2(50) NOT NULL, 
	TID VARCHAR2(50) NOT NULL, 
	ATTR VARCHAR2(500), 
	PRIMARY KEY (ID)
);

comment on column CMS_CHANNEL_TEMPLET.ID is '';
comment on column CMS_CHANNEL_TEMPLET.CHLID is '';
comment on column CMS_CHANNEL_TEMPLET.TID is '';
comment on column CMS_CHANNEL_TEMPLET.ATTR is '';


DROP TABLE CMS_DOCUMENT;
CREATE TABLE CMS_DOCUMENT (
	DOCID NUMBER(5,0) NOT NULL, 
	DOCCHANNEL VARCHAR2(200), 
	DOCVERSION VARCHAR2(200), 
	DOCORDER NUMBER(5,0), 
	DOCTYPE VARCHAR2(200), 
	DOCTITLE VARCHAR2(200), 
	DOCSOURCE VARCHAR2(200), 
	DOCSTATUS VARCHAR2(200), 
	DOCCONTENT CLOB, 
	DOCHTMLCON CLOB, 
	DOCABS VARCHAR2(400), 
	DOCKEYSWORD VARCHAR2(500), 
	DOCAUTHOR VARCHAR2(200), 
	DOCMAKEDATE DATE, 
	DOCPUBDATE DATE, 
	HITSCOUNT VARCHAR2(200), 
	ACCTACHPIC VARCHAR2(200), 
	DOCLINK VARCHAR2(400), 
	TEMPK VARCHAR2(500), 
	DOCRELTIME VARCHAR2(500), 
	CONSTRAINT PK_CMS_DOCUMENT PRIMARY KEY (DOCID)
);

comment on column CMS_DOCUMENT.DOCID is '';
comment on column CMS_DOCUMENT.DOCCHANNEL is '';
comment on column CMS_DOCUMENT.DOCVERSION is '';
comment on column CMS_DOCUMENT.DOCORDER is '';
comment on column CMS_DOCUMENT.DOCTYPE is '';
comment on column CMS_DOCUMENT.DOCTITLE is '';
comment on column CMS_DOCUMENT.DOCSOURCE is '';
comment on column CMS_DOCUMENT.DOCSTATUS is '';
comment on column CMS_DOCUMENT.DOCCONTENT is '';
comment on column CMS_DOCUMENT.DOCHTMLCON is '';
comment on column CMS_DOCUMENT.DOCABS is '';
comment on column CMS_DOCUMENT.DOCKEYSWORD is '';
comment on column CMS_DOCUMENT.DOCAUTHOR is '';
comment on column CMS_DOCUMENT.DOCMAKEDATE is '';
comment on column CMS_DOCUMENT.DOCPUBDATE is '';
comment on column CMS_DOCUMENT.HITSCOUNT is '';
comment on column CMS_DOCUMENT.ACCTACHPIC is '';
comment on column CMS_DOCUMENT.DOCLINK is '';
comment on column CMS_DOCUMENT.TEMPK is '';
comment on column CMS_DOCUMENT.DOCRELTIME is '';


DROP TABLE CMS_ENCLOSURE;
CREATE TABLE CMS_ENCLOSURE (
	ATTID NUMBER(5,0) NOT NULL, 
	ATTNAME VARCHAR2(500), 
	ATTDOCID VARCHAR2(50), 
	ATTDESC VARCHAR2(500), 
	ATTPATH VARCHAR2(500), 
	ATTTYPE VARCHAR2(50), 
	CONSTRAINT PK_CMS_ENCLOSURE PRIMARY KEY (ATTID)
);

comment on column CMS_ENCLOSURE.ATTID is '';
comment on column CMS_ENCLOSURE.ATTNAME is '';
comment on column CMS_ENCLOSURE.ATTDOCID is '';
comment on column CMS_ENCLOSURE.ATTDESC is '';
comment on column CMS_ENCLOSURE.ATTPATH is '';
comment on column CMS_ENCLOSURE.ATTTYPE is '';


DROP TABLE CMS_TEMPLET;
CREATE TABLE CMS_TEMPLET (
	TPLID NUMBER(5,0) NOT NULL, 
	TPLNAME VARCHAR2(500), 
	TPLEXTEND VARCHAR2(500), 
	TPLDESC VARCHAR2(500), 
	TPLPATH VARCHAR2(500), 
	TPLPRO VARCHAR2(500), 
	TPLCHNLID VARCHAR2(50), 
	TPLTYPE VARCHAR2(50), 
	TPLSTORENAME VARCHAR2(500), 
	CONSTRAINT PK_CMS_TEMPLET PRIMARY KEY (TPLID)
);

comment on column CMS_TEMPLET.TPLID is '';
comment on column CMS_TEMPLET.TPLNAME is '';
comment on column CMS_TEMPLET.TPLEXTEND is '';
comment on column CMS_TEMPLET.TPLDESC is '';
comment on column CMS_TEMPLET.TPLPATH is '';
comment on column CMS_TEMPLET.TPLPRO is '';
comment on column CMS_TEMPLET.TPLCHNLID is '';
comment on column CMS_TEMPLET.TPLTYPE is '';
comment on column CMS_TEMPLET.TPLSTORENAME is '';


DROP TABLE CMS_WEBINFO;
CREATE TABLE CMS_WEBINFO (
	ID NUMBER(5,0) NOT NULL, 
	PAGETYPE VARCHAR2(10), 
	AREA VARCHAR2(50), 
	CHANNELID VARCHAR2(50), 
	PATH VARCHAR2(50), 
	CREATETIME DATE, 
	LASTMODIFY DATE, 
	CONSTRAINT PK_CMS_WEBINFO PRIMARY KEY (ID)
);

comment on column CMS_WEBINFO.ID is '';
comment on column CMS_WEBINFO.PAGETYPE is '';
comment on column CMS_WEBINFO.AREA is '';
comment on column CMS_WEBINFO.CHANNELID is '';
comment on column CMS_WEBINFO.PATH is '';
comment on column CMS_WEBINFO.CREATETIME is '';
comment on column CMS_WEBINFO.LASTMODIFY is '';



DROP SEQUENCE CMS_CHANNEL_SEQ;
CREATE SEQUENCE CMS_CHANNEL_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


DROP SEQUENCE CMS_CHANNEL_TEMPLET_SEQ;
CREATE SEQUENCE CMS_CHANNEL_TEMPLET_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


DROP SEQUENCE CMS_DOCUMENT_SEQ;
CREATE SEQUENCE CMS_DOCUMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


DROP SEQUENCE CMS_ENCLOSURE_SEQ;
CREATE SEQUENCE CMS_ENCLOSURE_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


DROP SEQUENCE CMS_TEMPLET_SEQ;
CREATE SEQUENCE CMS_TEMPLET_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


DROP SEQUENCE CMS_WEBINFO_SEQ;
CREATE SEQUENCE CMS_WEBINFO_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCYCLE
    CACHE 20
    NOORDER
;


INSERT INTO CMS_CHANNEL (CHANNELID, SITEID, CHNLNAME, CHNLDESC, PARENTID, CHNLORDER, CHNLDATAPATH, CHNLTYPE, CHNLMAKEDATE, CRUSER, STATUS, LINKURL, CHNLDESNAME, CHNLDATASTATUS, CHANNELPOWER, MIRRORID) VALUES ((CMS_CHANNEL_SEQ.nextval), '2', 'sinatay', 'ChnlDesc', '0', 0, 'ChnlDataPath', '2', sysdate, 'CRUser', '0', '', '电子商务网销平台', 1, '0', null);