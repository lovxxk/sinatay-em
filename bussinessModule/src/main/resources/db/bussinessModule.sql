---------------------------------------------
-- Export file for user SINATAY            --
-- Created by MACOSX on 2013-8-5, 22:38:58 --
---------------------------------------------

spool bussinessModule.log

drop table ABNORMALORDERFORM;
drop table ADDRESSEE;
drop table ELECTRONICPOLICY;
drop table FUNDSTRANSFER;
drop table INSURANCEPOLICYCACHE;
drop table INSURANCEPOLICYLIABILITY;
drop table INSURANCEPOLICYPROCESS;
drop table INSUREDLIABILITY;
drop table INSUREINFORMBOOK;
drop table INVOICEITEM;
drop table INVOICE;
drop table ORDERFORM;
drop table PAYMENTACCOUNT;
drop table PARTYROLEINPOLICY;
drop table PARTNERINSTITUTION;
drop table INSURANCEPOLICY;

prompt
prompt Creating table ABNORMALORDERFORM
prompt ================================
prompt
create table ABNORMALORDERFORM
(
  SERIALNO             VARCHAR2(32) not null,
  ORDERSERIALNUMBER    VARCHAR2(50),
  APPLICATIONNUMBER    VARCHAR2(50),
  ORDERSTATUS          INTEGER,
  MANUALINSUREDSTATUS  INTEGER,
  PAYMENTMETHOD        INTEGER,
  PAYSTATUS            INTEGER,
  OPERATORID           VARCHAR2(32),
  EXCEPTIONCODE        VARCHAR2(50),
  EXCEPTIONDESCRIPTION VARCHAR2(255),
  EXCEPTIONSTACK       VARCHAR2(1000),
  SENDEMAILTIME        TIMESTAMP(6),
  CREATETIME           TIMESTAMP(6),
  UPDATETIME           TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column ABNORMALORDERFORM.SERIALNO
  is '序号';
comment on column ABNORMALORDERFORM.ORDERSERIALNUMBER
  is '订单号';
comment on column ABNORMALORDERFORM.APPLICATIONNUMBER
  is '投保单号';
comment on column ABNORMALORDERFORM.ORDERSTATUS
  is '订单状态';
comment on column ABNORMALORDERFORM.MANUALINSUREDSTATUS
  is '手动承保状态';
comment on column ABNORMALORDERFORM.PAYMENTMETHOD
  is '支付方式';
comment on column ABNORMALORDERFORM.PAYSTATUS
  is '支付状态';
comment on column ABNORMALORDERFORM.OPERATORID
  is '操作员';
comment on column ABNORMALORDERFORM.EXCEPTIONCODE
  is '异常代码';
comment on column ABNORMALORDERFORM.EXCEPTIONDESCRIPTION
  is '异常描述';
comment on column ABNORMALORDERFORM.EXCEPTIONSTACK
  is '异常堆栈';
comment on column ABNORMALORDERFORM.SENDEMAILTIME
  is '发送邮件时间';
comment on column ABNORMALORDERFORM.CREATETIME
  is '创建时间';
comment on column ABNORMALORDERFORM.UPDATETIME
  is '更新时间';
alter table ABNORMALORDERFORM
  add constraint ABNORMALORDERFORM_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ADDRESSEE
prompt ========================
prompt
create table ADDRESSEE
(
  SERIALNO         VARCHAR2(32) not null,
  CONSIGNEENAME    VARCHAR2(100),
  TELEPHONE        VARCHAR2(50),
  FIXEDPHONE       VARCHAR2(50),
  PROVINCE         VARCHAR2(255),
  CITY             VARCHAR2(255),
  COUNTY           VARCHAR2(255),
  CONSIGNEEADDRESS VARCHAR2(255),
  ZIPCODE          VARCHAR2(50),
  EMAIL            VARCHAR2(50),
  POLICYSERIALNO   VARCHAR2(32) not null,
  CREATETIME       TIMESTAMP(6),
  UPDATETIME       TIMESTAMP(6),
  REMARK           VARCHAR2(500)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 11M
    minextents 1
    maxextents unlimited
  );
comment on column ADDRESSEE.SERIALNO
  is '序号';
comment on column ADDRESSEE.CONSIGNEENAME
  is '收件人姓名';
comment on column ADDRESSEE.TELEPHONE
  is '联系电话';
comment on column ADDRESSEE.FIXEDPHONE
  is '固定电话';
comment on column ADDRESSEE.PROVINCE
  is '省';
comment on column ADDRESSEE.CITY
  is '市';
comment on column ADDRESSEE.COUNTY
  is '县/乡';
comment on column ADDRESSEE.CONSIGNEEADDRESS
  is '收件地址';
comment on column ADDRESSEE.ZIPCODE
  is '邮政编码';
comment on column ADDRESSEE.EMAIL
  is '电子邮箱';
comment on column ADDRESSEE.POLICYSERIALNO
  is '保单';
comment on column ADDRESSEE.CREATETIME
  is '创建时间';
comment on column ADDRESSEE.UPDATETIME
  is '更新时间';
comment on column ADDRESSEE.REMARK
  is '备注';
alter table ADDRESSEE
  add constraint ADDRESSEE_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    minextents 1
    maxextents unlimited
  );
alter table ADDRESSEE
  add constraint ADDRESS_POLICYSERIALNO_UK unique (POLICYSERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ELECTRONICPOLICY
prompt ===============================
prompt
create table ELECTRONICPOLICY
(
  SERIALNO         VARCHAR2(32) not null,
  COM_CODE         VARCHAR2(32),
  POLICY_NO        VARCHAR2(30),
  ORDER_NO         VARCHAR2(30),
  VAILD_FLAG       INTEGER,
  PATH             VARCHAR2(500),
  ABSOLUTEPATH     VARCHAR2(500),
  ITEMID           VARCHAR2(500),
  DATIME           TIMESTAMP(6),
  CMTIME           TIMESTAMP(6),
  CREATETIME       TIMESTAMP(6),
  UPDATETIME       TIMESTAMP(6),
  ISDOWNLOAD       INTEGER,
  DOWNLOADCOUNT    INTEGER,
  LASTDOWNLOADTIME TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
comment on column ELECTRONICPOLICY.SERIALNO
  is '序号（逻辑主键UUID生成策略）';
comment on column ELECTRONICPOLICY.COM_CODE
  is '分公司号';
comment on column ELECTRONICPOLICY.POLICY_NO
  is '保单号';
comment on column ELECTRONICPOLICY.ORDER_NO
  is '订单号';
comment on column ELECTRONICPOLICY.VAILD_FLAG
  is '有效标记，默认为1，有效；2为已上传CM，3为';
comment on column ELECTRONICPOLICY.PATH
  is '电子保单存储路径';
comment on column ELECTRONICPOLICY.ABSOLUTEPATH
  is '电子保单存储绝对路径';
comment on column ELECTRONICPOLICY.ITEMID
  is '对应上传CM后的ITEMID';
comment on column ELECTRONICPOLICY.DATIME
  is '上传时间，取数据写入的current timestamp';
comment on column ELECTRONICPOLICY.CMTIME
  is '上传CM时间，取上传CM操作的current timestamp';
comment on column ELECTRONICPOLICY.CREATETIME
  is '创建时间';
comment on column ELECTRONICPOLICY.UPDATETIME
  is '数据更新时间';
comment on column ELECTRONICPOLICY.ISDOWNLOAD
  is '是否下载过';
comment on column ELECTRONICPOLICY.DOWNLOADCOUNT
  is '下载次数';
comment on column ELECTRONICPOLICY.LASTDOWNLOADTIME
  is '最后一次下载时间';
alter table ELECTRONICPOLICY
  add constraint AK_EPOLICY_PK_EPOLICY primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table INSURANCEPOLICY
prompt ==============================
prompt
create table INSURANCEPOLICY
(
  SERIALNO                       VARCHAR2(32) not null,
  TRANSIDENTIFY                  VARCHAR2(50),
  TRANSSERIALNUMBER              VARCHAR2(50),
  MERCHANTTRANSSERIALNUMBER      VARCHAR2(50),
  ORDERSERIALNUMBER              VARCHAR2(50),
  MERCHANTORDERNUMBER            VARCHAR2(50),
  POLICYSERIALNUMBER             VARCHAR2(50),
  APPLICATIONNUMBER              VARCHAR2(50),
  APPLICATIONSERIALNUMBER        VARCHAR2(50),
  ELECTRONICPOLICYNUMBER         VARCHAR2(50),
  ELECTRONICAPPLICANTNUMBER      VARCHAR2(50),
  POLICYTYPE                     INTEGER,
  DOCUMENTTYPE                   INTEGER,
  OVERDUEPREMIUMPAYMENTOPTION    INTEGER,
  POLICYSTATUS                   INTEGER,
  POLICYSTATUSNAME               VARCHAR2(50),
  POLICYSTATUSDESC               VARCHAR2(1000),
  GENERATEELECTRONICPOLICYSTATUS INTEGER,
  PRODUCTCODE                    VARCHAR2(50),
  PRODUCTNAME                    VARCHAR2(255),
  MERCHANTPRODUCTCODE            VARCHAR2(50),
  MERCHANTPRODUCTNAME            VARCHAR2(255),
  COMBOCODE                      VARCHAR2(50),
  COMBONAME                      VARCHAR2(255),
  INSUREDAMOUNT                  NUMBER(15,2),
  PREMIUM                        NUMBER(15,2),
  DISCOUNTPREMIUM                NUMBER(15,2),
  GROSSPREMIUM                   NUMBER(15,2),
  FACEAMOUNT                     NUMBER(15,2),
  FIRSTPREMIUM                   NUMBER(15,2),
  INITIALPREMAMT                 NUMBER(15,2),
  BENEFITPERIOD                  INTEGER,
  BENEFITPERIODTYPE              INTEGER,
  PAYMENTMODE                    INTEGER,
  PAYMENTDURATION                INTEGER,
  PAYMENTDURATIONMODE            INTEGER,
  SIGNEDDATE                     DATE,
  INCEPTIONDATE                  DATE,
  APPLICATIONDATE                DATE,
  BACKTRACKDATE                  DATE,
  INSURANCESTARTPERIOD           TIMESTAMP(6),
  INSURANCEENDPERIOD             TIMESTAMP(6),
  SUBMISSIONDATE                 DATE,
  BENEFICIARYMODE                INTEGER,
  TRAVELDESTINATION              VARCHAR2(50),
  PAYMENTMETHOD                  INTEGER,
  BENEFITMODE                    INTEGER,
  DIVTYPE                        INTEGER,
  ANNUITYPAYOUTDURATION          VARCHAR2(10),
  ANNUITYPAYOUTDURATIONMODE      INTEGER,
  PAYOUTSTART                    VARCHAR2(10),
  PAYOUTEND                      VARCHAR2(10),
  EXCESSPREMAMT                  NUMBER(15,2),
  POLICYDELIVERYFEE              NUMBER(15,2),
  POLICYSTATUSMESSAGE            VARCHAR2(500),
  UNITCOUNT                      INTEGER,
  FORMID                         VARCHAR2(100),
  SPECIALSTATEMENT               VARCHAR2(500),
  CONTRACTNAMES                  VARCHAR2(500),
  FUNDTRANSFERDATEBASEDON        INTEGER,
  BANKCODE                       VARCHAR2(20),
  REQUIREVISA                    INTEGER,
  CERTIFICATIONREQUIRED          INTEGER,
  DELIVERYINVOICE                INTEGER,
  DELIVERYEPOLICY                INTEGER,
  DELIVERYHARDCOPY               INTEGER,
  CAMPAIGNCODE                   VARCHAR2(20),
  CAMPAIGNNAME                   VARCHAR2(100),
  DISCOUNTTYPECODE               VARCHAR2(20),
  DISCOUNTRATE                   NUMBER(15,2),
  AUTORENEWABLE                  INTEGER,
  AGENTCODE                      VARCHAR2(20),
  AGREEMENTNO                    VARCHAR2(100),
  AGENTNAME                      VARCHAR2(255),
  DEPARTMENTNO                   VARCHAR2(20),
  DEPARTMENTNAME                 VARCHAR2(255),
  INSTITUTIONCODE                VARCHAR2(20),
  INSTITUTIONNAME                VARCHAR2(255),
  INTERMEDIARYCODE               VARCHAR2(20),
  INTERMEDIARYNAME               VARCHAR2(255),
  BRANCHCODE                     VARCHAR2(20),
  BRANCHNAME                     VARCHAR2(255),
  ORGANIZATIONCODE               VARCHAR2(20),
  ORGANIZATIONNAME               VARCHAR2(255),
  PERSONALUSERSERIALNO           VARCHAR2(32),
  SYNCSTATUS                     INTEGER,
  SYNCSTATUSDESC                 VARCHAR2(1000),
  SYNCSTARTTIME                  TIMESTAMP(6),
  SYNCENDTIME                    TIMESTAMP(6),
  CASHVALUE                      NUMBER(15,2),
  ACTUALAVAILABLEAMOUNT          NUMBER(15,2),
  REVERSALFLAG                   INTEGER,
  MUSTCANCELFLAG                 INTEGER,
  ALLOWPARTYWITHDROWFLAG         INTEGER,
  PRESERVEACCEPTTIME             TIMESTAMP(6),
  PRESERVEEFFECTIVETIME          TIMESTAMP(6),
  REFUNDPOLICYTIME               TIMESTAMP(6),
  REFUNDPOLICYSUCCESSTIME        TIMESTAMP(6),
  CUSTOMERID                     VARCHAR2(32),
  EXPAYMODE                      INTEGER,
  GETPOLMODE                     INTEGER,
  PASSWORD                     	 VARCHAR2(100),
  SPECCONTENT                    VARCHAR2(100),
  TEMPFEENO                      VARCHAR2(100),
  AGENTGROUP                     VARCHAR2(100),
  DISPUTEDFLAG                   INTEGER,
  ACNAME                     	 VARCHAR2(100),
  ISVISIT                        INTEGER,
  ISBIND                         INTEGER,
  INSUREINFORMBOOKNUMBER         INTEGER,
  OPERATORID                     VARCHAR2(32),
  QUOTENO                    VARCHAR2(32),
  RENEWALFLAG                    VARCHAR2(10),
  OLDLPOLICYNO                    VARCHAR2(32),
  CURRENCY                    VARCHAR2(20),
  INPUTHOUR                    VARCHAR2(20),
  BUSINESSAREA                    VARCHAR2(20),
  REASON                    VARCHAR2(255),
  FLAG                    VARCHAR2(10),
  PRECHECKDATE                    VARCHAR2(32),
  MOREBUYNO                    VARCHAR2(255),
  BUSINESSSOURCE                    VARCHAR2(32),
  GROUPCHANNEL                    VARCHAR2(32),
  CREATETIME                     TIMESTAMP(6),
  UPDATETIME                     TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 37M
    minextents 1
    maxextents unlimited
  );
comment on column INSURANCEPOLICY.SERIALNO
  is '序号';
comment on column INSURANCEPOLICY.TRANSIDENTIFY
  is '交易标示';
comment on column INSURANCEPOLICY.TRANSSERIALNUMBER
  is '交易流水号';
comment on column INSURANCEPOLICY.MERCHANTTRANSSERIALNUMBER
  is '商家交易流水号';
comment on column INSURANCEPOLICY.ORDERSERIALNUMBER
  is '订单号';
comment on column INSURANCEPOLICY.MERCHANTORDERNUMBER
  is '合作机构订单号';
comment on column INSURANCEPOLICY.POLICYSERIALNUMBER
  is '保单号';
comment on column INSURANCEPOLICY.APPLICATIONNUMBER
  is '投保单号';
comment on column INSURANCEPOLICY.APPLICATIONSERIALNUMBER
  is '投保单印刷号';
comment on column INSURANCEPOLICY.ELECTRONICPOLICYNUMBER
  is '电子保单号';
comment on column INSURANCEPOLICY.ELECTRONICAPPLICANTNUMBER
  is '电子投保单号';
comment on column INSURANCEPOLICY.POLICYTYPE
  is '保单类型';
comment on column INSURANCEPOLICY.DOCUMENTTYPE
  is '保险单证类型';
comment on column INSURANCEPOLICY.OVERDUEPREMIUMPAYMENTOPTION
  is '逾期缴付保费选项(保费过期未付选择)';
comment on column INSURANCEPOLICY.POLICYSTATUS
  is '保单状态';
comment on column INSURANCEPOLICY.POLICYSTATUSNAME
  is '保单状态名称';
comment on column INSURANCEPOLICY.POLICYSTATUSDESC
  is '保单状态描述';
comment on column INSURANCEPOLICY.GENERATEELECTRONICPOLICYSTATUS
  is '生成电子保单状态';
comment on column INSURANCEPOLICY.PRODUCTCODE
  is '产品代码';
comment on column INSURANCEPOLICY.PRODUCTNAME
  is '产品名称';
comment on column INSURANCEPOLICY.MERCHANTPRODUCTCODE
  is '合作机构产品代码';
comment on column INSURANCEPOLICY.MERCHANTPRODUCTNAME
  is '合作机构产品名称';
comment on column INSURANCEPOLICY.COMBOCODE
  is '套餐代码';
comment on column INSURANCEPOLICY.COMBONAME
  is '套餐名称';
comment on column INSURANCEPOLICY.INSUREDAMOUNT
  is '保额';
comment on column INSURANCEPOLICY.PREMIUM
  is '保费';
comment on column INSURANCEPOLICY.DISCOUNTPREMIUM
  is '折扣保费';
comment on column INSURANCEPOLICY.GROSSPREMIUM
  is '总保费';
comment on column INSURANCEPOLICY.FACEAMOUNT
  is '总保额';
comment on column INSURANCEPOLICY.FIRSTPREMIUM
  is '首期保费';
comment on column INSURANCEPOLICY.INITIALPREMAMT
  is '首次缴费金额';
comment on column INSURANCEPOLICY.BENEFITPERIOD
  is '保障年期';
comment on column INSURANCEPOLICY.BENEFITPERIODTYPE
  is '保障年期类型';
comment on column INSURANCEPOLICY.PAYMENTMODE
  is '缴费方式';
comment on column INSURANCEPOLICY.PAYMENTDURATION
  is '缴费年期';
comment on column INSURANCEPOLICY.PAYMENTDURATIONMODE
  is '缴费年期类型';
comment on column INSURANCEPOLICY.SIGNEDDATE
  is '签单日期';
comment on column INSURANCEPOLICY.INCEPTIONDATE
  is '保单生效日';
comment on column INSURANCEPOLICY.APPLICATIONDATE
  is '保单申请日';
comment on column INSURANCEPOLICY.BACKTRACKDATE
  is '保单回溯日';
comment on column INSURANCEPOLICY.INSURANCESTARTPERIOD
  is '保险起期';
comment on column INSURANCEPOLICY.INSURANCEENDPERIOD
  is '保险止期';
comment on column INSURANCEPOLICY.SUBMISSIONDATE
  is '投保受理日期';
comment on column INSURANCEPOLICY.BENEFICIARYMODE
  is '受益方式';
comment on column INSURANCEPOLICY.TRAVELDESTINATION
  is '旅游目的地';
comment on column INSURANCEPOLICY.PAYMENTMETHOD
  is '支付方式';
comment on column INSURANCEPOLICY.BENEFITMODE
  is '生存金领取方式';
comment on column INSURANCEPOLICY.DIVTYPE
  is '红利领取方式';
comment on column INSURANCEPOLICY.ANNUITYPAYOUTDURATION
  is '年金领取期限';
comment on column INSURANCEPOLICY.ANNUITYPAYOUTDURATIONMODE
  is '年金领取期限类型';
comment on column INSURANCEPOLICY.PAYOUTSTART
  is '金领取起始年龄';
comment on column INSURANCEPOLICY.PAYOUTEND
  is '年金领取终止年龄';
comment on column INSURANCEPOLICY.EXCESSPREMAMT
  is '附加保费/手续金额';
comment on column INSURANCEPOLICY.POLICYDELIVERYFEE
  is '纸质保单快递费';
comment on column INSURANCEPOLICY.POLICYSTATUSMESSAGE
  is '自动核保警告';
comment on column INSURANCEPOLICY.UNITCOUNT
  is '份数';
comment on column INSURANCEPOLICY.FORMID
  is '投保书类型';
comment on column INSURANCEPOLICY.SPECIALSTATEMENT
  is '特别声明';
comment on column INSURANCEPOLICY.CONTRACTNAMES
  is '阅读并理解相关信息';
comment on column INSURANCEPOLICY.FUNDTRANSFERDATEBASEDON
  is '保费进入投资账户日期选项';
comment on column INSURANCEPOLICY.BANKCODE
  is '缴费银行/代理银行编码';
comment on column INSURANCEPOLICY.REQUIREVISA
  is '是否需要签证';
comment on column INSURANCEPOLICY.CERTIFICATIONREQUIRED
  is '是否需要英文证明';
comment on column INSURANCEPOLICY.DELIVERYINVOICE
  is '是否提供发票';
comment on column INSURANCEPOLICY.DELIVERYEPOLICY
  is '是否需要电子保单';
comment on column INSURANCEPOLICY.DELIVERYHARDCOPY
  is '是否投递纸质保单';
comment on column INSURANCEPOLICY.CAMPAIGNCODE
  is '活动代码';
comment on column INSURANCEPOLICY.CAMPAIGNNAME
  is '活动名称';
comment on column INSURANCEPOLICY.DISCOUNTTYPECODE
  is '折扣代码';
comment on column INSURANCEPOLICY.DISCOUNTRATE
  is '折扣系数';
comment on column INSURANCEPOLICY.AUTORENEWABLE
  is '否申请一年期自动续保 ';
comment on column INSURANCEPOLICY.AGENTCODE
  is '代理人代码';
comment on column INSURANCEPOLICY.AGREEMENTNO
  is '代理人协议号';
comment on column INSURANCEPOLICY.AGENTNAME
  is '代理人姓名';
comment on column INSURANCEPOLICY.DEPARTMENTNO
  is '代理人所在机构编码';
comment on column INSURANCEPOLICY.DEPARTMENTNAME
  is '代理人所在机构名称';
comment on column INSURANCEPOLICY.INSTITUTIONCODE
  is '渠道代码';
comment on column INSURANCEPOLICY.INSTITUTIONNAME
  is '渠道名称';
comment on column INSURANCEPOLICY.INTERMEDIARYCODE
  is '机构代码 ';
comment on column INSURANCEPOLICY.INTERMEDIARYNAME
  is '机构名称';
comment on column INSURANCEPOLICY.BRANCHCODE
  is '网点代码';
comment on column INSURANCEPOLICY.BRANCHNAME
  is '网点名称';
comment on column INSURANCEPOLICY.ORGANIZATIONCODE
  is '合作机构代码';
comment on column INSURANCEPOLICY.ORGANIZATIONNAME
  is '合作机构名称';
comment on column INSURANCEPOLICY.PERSONALUSERSERIALNO
  is '个人客户';
comment on column INSURANCEPOLICY.SYNCSTATUS
  is '同步状态';
comment on column INSURANCEPOLICY.SYNCSTATUSDESC
  is '同步状态描述';
comment on column INSURANCEPOLICY.SYNCSTARTTIME
  is '同步开始时间';
comment on column INSURANCEPOLICY.SYNCENDTIME
  is '同步结束时间';
comment on column INSURANCEPOLICY.CASHVALUE
  is '现金价值';
comment on column INSURANCEPOLICY.ACTUALAVAILABLEAMOUNT
  is '实际可支取金额';
comment on column INSURANCEPOLICY.REVERSALFLAG
  is '是否可冲正';
comment on column INSURANCEPOLICY.MUSTCANCELFLAG
  is '全额情况下领取是否必须退保 ';
comment on column INSURANCEPOLICY.ALLOWPARTYWITHDROWFLAG
  is '是否允许部分支取';
comment on column INSURANCEPOLICY.PRESERVEACCEPTTIME
  is '保全受理时间';
comment on column INSURANCEPOLICY.PRESERVEEFFECTIVETIME
  is '保全生效时间';
comment on column INSURANCEPOLICY.REFUNDPOLICYTIME
  is '撤单时间';
comment on column INSURANCEPOLICY.REFUNDPOLICYSUCCESSTIME
  is '撤单成功时间';
comment on column INSURANCEPOLICY.CUSTOMERID
  is '用户号';
comment on column INSURANCEPOLICY.EXPAYMODE
  is '续期缴费形式';
comment on column INSURANCEPOLICY.GETPOLMODE
  is '保单递送方式';
comment on column INSURANCEPOLICY.PASSWORD
  is '保单密码';
comment on column INSURANCEPOLICY.SPECCONTENT
  is '特别约定';
comment on column INSURANCEPOLICY.TEMPFEENO
  is '发票印刷号码';
comment on column INSURANCEPOLICY.AGENTGROUP
  is '代理人组别';
comment on column INSURANCEPOLICY.DISPUTEDFLAG
  is '合同争议处理方式';
comment on column INSURANCEPOLICY.ACNAME
  is '仲裁委员会名称';
comment on column INSURANCEPOLICY.ISVISIT
  is '是否回访';
comment on column INSURANCEPOLICY.ISBIND
  is '绑定标志';
comment on column INSURANCEPOLICY.INSUREINFORMBOOKNUMBER
  is '告知数';
comment on column INSURANCEPOLICY.OPERATORID
  is '操作员';
comment on column INSURANCEPOLICY.CREATETIME
  is '创建时间';
comment on column INSURANCEPOLICY.UPDATETIME
  is '更新时间';
alter table INSURANCEPOLICY
  add constraint AK_INSURANCEPOLICYBAS_INSURANC primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 6M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table FUNDSTRANSFER
prompt ============================
prompt
create table FUNDSTRANSFER
(
  SERIALNO                  VARCHAR2(32) not null,
  FUNDSTRANSFERSERIALNUMBER VARCHAR2(50),
  MERCHANTORDERNUMBER       VARCHAR2(50),
  FUNDSTRANSFERORDERNUMBER  VARCHAR2(50),
  POLICYSERIALNUMBER        VARCHAR2(50),
  ACCOUNTNUMBER             VARCHAR2(100),
  ACCTHOLDERID              VARCHAR2(100),
  ACCTHOLDERNAME            VARCHAR2(100),
  CASHVALUE                 NUMBER(15,2),
  ACTUALAVAILABLEAMOUNT     NUMBER(15,2),
  TRANSFERTYPE              INTEGER,
  TRANSFERAMOUNT            NUMBER(15,2),
  TRANSFERTIME              TIMESTAMP(6),
  PRESERVEACCEPTTIME        TIMESTAMP(6),
  PRESERVEEFFECTIVETIME     TIMESTAMP(6),
  TRANSFERSUCCESSTIME       TIMESTAMP(6),
  FUNDSTRANSFERSTATUS       INTEGER,
  FUNDSTRANSFERSTATUSDESC   VARCHAR2(1000),
  CHECKPAYNUMBER            VARCHAR2(50),
  ACCOUNTDATE               TIMESTAMP(6),
  REFUNDPOLICYTIME          TIMESTAMP(6),
  REFUNDPOLICYSUCCESSTIME   TIMESTAMP(6),
  SYNCSTATUS                INTEGER,
  SYNCSTATUSDESC            VARCHAR2(1000),
  SYNCSTARTTIME             TIMESTAMP(6),
  SYNCENDTIME               TIMESTAMP(6),
  OPERATORID                VARCHAR2(32),
  POLICYSERIALNO            VARCHAR2(32),
  CREATETIME                TIMESTAMP(6),
  UPDATETIME                TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column FUNDSTRANSFER.SERIALNO
  is '序号';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSERIALNUMBER
  is '资金划拨流水号';
comment on column FUNDSTRANSFER.MERCHANTORDERNUMBER
  is '合作机构订单号（淘宝订单号）';
comment on column FUNDSTRANSFER.FUNDSTRANSFERORDERNUMBER
  is '资金划拨订单号';
comment on column FUNDSTRANSFER.POLICYSERIALNUMBER
  is '保单号';
comment on column FUNDSTRANSFER.ACCOUNTNUMBER
  is '资金划拨账号';
comment on column FUNDSTRANSFER.ACCTHOLDERID
  is '帐号持有人Id';
comment on column FUNDSTRANSFER.ACCTHOLDERNAME
  is '帐号持有人姓名';
comment on column FUNDSTRANSFER.CASHVALUE
  is '现金价值';
comment on column FUNDSTRANSFER.ACTUALAVAILABLEAMOUNT
  is '实际可支取金额';
comment on column FUNDSTRANSFER.TRANSFERTYPE
  is '划拨类型';
comment on column FUNDSTRANSFER.TRANSFERAMOUNT
  is '划拨金额';
comment on column FUNDSTRANSFER.TRANSFERTIME
  is '划拨时间';
comment on column FUNDSTRANSFER.PRESERVEACCEPTTIME
  is '保全受理时间';
comment on column FUNDSTRANSFER.PRESERVEEFFECTIVETIME
  is '保全生效时间';
comment on column FUNDSTRANSFER.TRANSFERSUCCESSTIME
  is '划拨成功时间';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSTATUS
  is '资金划拨状态';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSTATUSDESC
  is '资金划拨状态描述';
comment on column FUNDSTRANSFER.CHECKPAYNUMBER
  is '对账单号';
comment on column FUNDSTRANSFER.ACCOUNTDATE
  is '对账日期';
comment on column FUNDSTRANSFER.REFUNDPOLICYTIME
  is '撤单时间';
comment on column FUNDSTRANSFER.REFUNDPOLICYSUCCESSTIME
  is '撤单成功时间';
comment on column FUNDSTRANSFER.SYNCSTATUS
  is '同步状态';
comment on column FUNDSTRANSFER.SYNCSTATUSDESC
  is '同步状态描述';
comment on column FUNDSTRANSFER.SYNCSTARTTIME
  is '同步开始时间';
comment on column FUNDSTRANSFER.SYNCENDTIME
  is '同步结束时间';
comment on column FUNDSTRANSFER.OPERATORID
  is '操作员';
comment on column FUNDSTRANSFER.POLICYSERIALNO
  is '保单';
comment on column FUNDSTRANSFER.CREATETIME
  is '创建时间';
comment on column FUNDSTRANSFER.UPDATETIME
  is '更新时间';
alter table FUNDSTRANSFER
  add constraint FUNDSTRANSFER_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table FUNDSTRANSFER
  add constraint FUNDSTRANSFER_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);

prompt
prompt Creating table INSURANCEPOLICYCACHE
prompt ===================================
prompt
create table INSURANCEPOLICYCACHE
(
  SERIALNO            VARCHAR2(32) not null,
  MERCHANTORDERNUMBER VARCHAR2(50),
  POLICYSERIALNUMBER  VARCHAR2(50),
  INSTITUTIONCODE     VARCHAR2(20),
  INSTITUTIONNAME     VARCHAR2(255),
  REQUESTTIME         TIMESTAMP(6),
  LASTREQUESTTIME     TIMESTAMP(6),
  CREATETIME          TIMESTAMP(6),
  UPDATETIME          TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column INSURANCEPOLICYCACHE.SERIALNO
  is '序号';
comment on column INSURANCEPOLICYCACHE.MERCHANTORDERNUMBER
  is '商家订单号';
comment on column INSURANCEPOLICYCACHE.POLICYSERIALNUMBER
  is '保单号';
comment on column INSURANCEPOLICYCACHE.INSTITUTIONCODE
  is '合作机构代码';
comment on column INSURANCEPOLICYCACHE.INSTITUTIONNAME
  is '合作机构名称';
comment on column INSURANCEPOLICYCACHE.REQUESTTIME
  is '第一次请求时间';
comment on column INSURANCEPOLICYCACHE.LASTREQUESTTIME
  is '最后一次请求时间';
comment on column INSURANCEPOLICYCACHE.CREATETIME
  is '创建时间';
comment on column INSURANCEPOLICYCACHE.UPDATETIME
  is '更新时间';

prompt
prompt Creating table INSURANCEPOLICYLIABILITY
prompt =======================================
prompt
create table INSURANCEPOLICYLIABILITY
(
  SERIALNO              VARCHAR2(32) not null,
  LIABILITYID           VARCHAR2(20),
  LIABILITYCODE         VARCHAR2(20),
  CORECODE              VARCHAR2(20),
  LIABILITYNAME         VARCHAR2(255),
  PRODUCTCODE           VARCHAR2(20),
  PRODUCTNAME           VARCHAR2(255),
  MERCHANTPRODUCTCODE   VARCHAR2(20),
  MERCHANTPRODUCTNAME   VARCHAR2(255),
  MERCHANTLIABILITYCODE VARCHAR2(20),
  MERCHANTLIABILITYNAME VARCHAR2(255),
  SUBRISKFLAG           INTEGER,
  LIABILITYORDER        INTEGER,
  GROUPTYPE             INTEGER,
  INSUREDAMOUNT         NUMBER(15,2),
  PREMIUM               NUMBER(15,2),
  DISCOUNTPREMIUM       NUMBER(15,2),
  DISCOUNTTYPECODE      VARCHAR2(20),
  DISCOUNTRATE          NUMBER(15,2),
  BENEFITPERIOD         INTEGER,
  BENEFITPERIODTYPE     INTEGER,
  INSURANCESTARTPERIOD  TIMESTAMP(6),
  INSURANCEENDPERIOD    TIMESTAMP(6),
  PAYMENTMODE           INTEGER,
  PAYMENTDURATION       INTEGER,
  PAYMENTDURATIONMODE   INTEGER,
  DEDUCTIBLES           NUMBER(15,2),
  LOSSRATIO             NUMBER(15,2),
  SEQINDEX              INTEGER,
  POLICYSERIALNO        VARCHAR2(32),
  PARENTSERIALNO        VARCHAR2(32),
  MAINRISKCODE		    VARCHAR2(20),
  RISKTYPE   			INTEGER,
  RATE   				NUMBER(15,2),
  INCEPTIONDATE   		DATE,
  RANK   				VARCHAR2(20),
  UNITCOUNT   			INTEGER,
  COSTINTV   			VARCHAR2(20),
  COSTDATE   			TIMESTAMP(6),
  SPECCONTENT   		VARCHAR2(200),
  PAYENDYEARFLAG   		INTEGER,
  PAYENDYEAR   			INTEGER,
  GETYEARFLAG   		INTEGER,
  GETYEAR   			INTEGER,
  INSUYEARFLAG   		INTEGER,
  INSUYEAR   			INTEGER,
  GETINTV   			INTEGER,
  GETBANKCODE   		VARCHAR2(20),
  GETBANKACCNO   		VARCHAR2(100),
  GETACCNAME   			VARCHAR2(100),
  GETACCPROVINCE   		VARCHAR2(20),
  GETACCCITY   			VARCHAR2(20),
  GETACCTYPE   			INTEGER,
  AUTOPAYFLAG   		INTEGER,
  BONUSPAYMODE   		INTEGER,
  SUBFLAG   			INTEGER,
  BONUSGETMODE   		INTEGER,
  AUTORNEWFLAG   		INTEGER,
  HEALTHFLAG   			INTEGER,
  FULLBONUSGETMODE   	INTEGER,
  FIRSTRATE   			NUMBER(15,2),
  SURERATE   			NUMBER(15,2),
  OPERATORID            VARCHAR2(32),
  CREATETIME            TIMESTAMP(6),
  UPDATETIME            TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 15M
    minextents 1
    maxextents unlimited
  );
comment on column INSURANCEPOLICYLIABILITY.SERIALNO
  is '序号';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYID
  is '保障/险种ID';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYCODE
  is '保障/险种代码';
comment on column INSURANCEPOLICYLIABILITY.CORECODE
  is '核心保障/险种代码';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYNAME
  is '保障/险种名称';
comment on column INSURANCEPOLICYLIABILITY.PRODUCTCODE
  is '产品代码';
comment on column INSURANCEPOLICYLIABILITY.PRODUCTNAME
  is '产品名称';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTPRODUCTCODE
  is '商家产品代码';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTPRODUCTNAME
  is '商家产品名称';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTLIABILITYCODE
  is '商家险种代码';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTLIABILITYNAME
  is '商家险种代码';
comment on column INSURANCEPOLICYLIABILITY.SUBRISKFLAG
  is '主附险标志';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYORDER
  is '保障/险种顺序';
comment on column INSURANCEPOLICYLIABILITY.GROUPTYPE
  is '群体类型';
comment on column INSURANCEPOLICYLIABILITY.INSUREDAMOUNT
  is '保额';
comment on column INSURANCEPOLICYLIABILITY.PREMIUM
  is '保费';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTPREMIUM
  is '折扣保费 ';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTTYPECODE
  is '折扣代码';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTRATE
  is '折扣系数 ';
comment on column INSURANCEPOLICYLIABILITY.BENEFITPERIOD
  is '保障年期';
comment on column INSURANCEPOLICYLIABILITY.BENEFITPERIODTYPE
  is '保障年期类型';
comment on column INSURANCEPOLICYLIABILITY.INSURANCESTARTPERIOD
  is '保险起期';
comment on column INSURANCEPOLICYLIABILITY.INSURANCEENDPERIOD
  is '保险止期';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTMODE
  is '缴费方式';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTDURATION
  is '缴费年期';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTDURATIONMODE
  is '缴费年期类型';
comment on column INSURANCEPOLICYLIABILITY.DEDUCTIBLES
  is '免赔额';
comment on column INSURANCEPOLICYLIABILITY.LOSSRATIO
  is '赔付比率';
comment on column INSURANCEPOLICYLIABILITY.SEQINDEX
  is '显示顺序';
comment on column INSURANCEPOLICYLIABILITY.POLICYSERIALNO
  is '保单';
comment on column INSURANCEPOLICYLIABILITY.PARENTSERIALNO
  is '父保障/险种代码';
comment on column INSURANCEPOLICYLIABILITY.MAINRISKCODE
  is '主险险种代码';
comment on column INSURANCEPOLICYLIABILITY.RISKTYPE
  is '险种类型';
comment on column INSURANCEPOLICYLIABILITY.RATE
  is '费率';
comment on column INSURANCEPOLICYLIABILITY.INCEPTIONDATE
  is '生效日';
comment on column INSURANCEPOLICYLIABILITY.RANK
  is '档次';
comment on column INSURANCEPOLICYLIABILITY.UNITCOUNT
  is '份数';
comment on column INSURANCEPOLICYLIABILITY.COSTINTV
  is '扣款间隔';
comment on column INSURANCEPOLICYLIABILITY.COSTDATE
  is '扣款时间';
comment on column INSURANCEPOLICYLIABILITY.SPECCONTENT
  is '特别约定';
comment on column INSURANCEPOLICYLIABILITY.PAYENDYEARFLAG
  is '缴费年期年龄标志';
comment on column INSURANCEPOLICYLIABILITY.PAYENDYEAR
  is '缴费年期年龄';
comment on column INSURANCEPOLICYLIABILITY.GETYEARFLAG
  is '领取年龄年期标志';
comment on column INSURANCEPOLICYLIABILITY.GETYEAR
  is '领取年龄';
comment on column INSURANCEPOLICYLIABILITY.INSUYEARFLAG
  is '保险年期年龄标志';
comment on column INSURANCEPOLICYLIABILITY.INSUYEAR
  is '保险年期年龄';
comment on column INSURANCEPOLICYLIABILITY.GETINTV
  is '领取方式';
comment on column INSURANCEPOLICYLIABILITY.GETBANKCODE
  is '领取银行编码';
comment on column INSURANCEPOLICYLIABILITY.GETBANKACCNO
  is '领取银行账户';
comment on column INSURANCEPOLICYLIABILITY.GETACCNAME
  is '领取银行户名';
comment on column INSURANCEPOLICYLIABILITY.GETACCPROVINCE
  is '领取银行省编码';
comment on column INSURANCEPOLICYLIABILITY.GETACCCITY
  is '领取银行市编码';
comment on column INSURANCEPOLICYLIABILITY.GETACCTYPE
  is '领取银行卡折类型';
comment on column INSURANCEPOLICYLIABILITY.AUTOPAYFLAG
  is '垫交标志';
comment on column INSURANCEPOLICYLIABILITY.BONUSPAYMODE
  is '红利分配标记';
comment on column INSURANCEPOLICYLIABILITY.SUBFLAG
  is '减额交清标志';
comment on column INSURANCEPOLICYLIABILITY.BONUSGETMODE
  is '红利领取方式';
comment on column INSURANCEPOLICYLIABILITY.AUTORNEWFLAG
  is '自动续交标志';
comment on column INSURANCEPOLICYLIABILITY.HEALTHFLAG
  is '健康告之标志';
comment on column INSURANCEPOLICYLIABILITY.FULLBONUSGETMODE
  is '满期领取金领取方式';
comment on column INSURANCEPOLICYLIABILITY.FIRSTRATE
  is '初始费用率';
comment on column INSURANCEPOLICYLIABILITY.SURERATE
  is '保证利率';
comment on column INSURANCEPOLICYLIABILITY.OPERATORID
  is '操作员';
comment on column INSURANCEPOLICYLIABILITY.CREATETIME
  is '创建时间';
comment on column INSURANCEPOLICYLIABILITY.UPDATETIME
  is '更新时间';
alter table INSURANCEPOLICYLIABILITY
  add constraint PLLIABILITY_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    minextents 1
    maxextents unlimited
  );
alter table INSURANCEPOLICYLIABILITY
  add constraint PLLIABILITY_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);
alter table INSURANCEPOLICYLIABILITY
  add constraint PLLIABILITY_RT_SF_FK foreign key (PARENTSERIALNO)
  references INSURANCEPOLICYLIABILITY (SERIALNO);

prompt
prompt Creating table INSURANCEPOLICYPROCESS
prompt =====================================
prompt
create table INSURANCEPOLICYPROCESS
(
  SERIALNO            VARCHAR2(32) not null,
  MERCHANTORDERNUMBER VARCHAR2(50),
  POLICYSERIALNUMBER  VARCHAR2(50),
  INSTITUTIONCODE     VARCHAR2(20),
  INSTITUTIONNAME     VARCHAR2(255),
  SERVICENAME         VARCHAR2(50),
  LOCKSTATUS          INTEGER,
  LOCKEDREASON        VARCHAR2(1000),
  PROCESSSTATUS       INTEGER,
  PROCESSSTARTTIME    TIMESTAMP(6),
  PROCESSENDTIME      TIMESTAMP(6),
  PROCESSSTATUSDESC   VARCHAR2(1000),
  CREATETIME          TIMESTAMP(6),
  UPDATETIME          TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 6M
    minextents 1
    maxextents unlimited
  );
comment on column INSURANCEPOLICYPROCESS.SERIALNO
  is '序号';
comment on column INSURANCEPOLICYPROCESS.MERCHANTORDERNUMBER
  is '淘宝订单号';
comment on column INSURANCEPOLICYPROCESS.POLICYSERIALNUMBER
  is '保单号';
comment on column INSURANCEPOLICYPROCESS.INSTITUTIONCODE
  is '合作机构代码';
comment on column INSURANCEPOLICYPROCESS.INSTITUTIONNAME
  is '合作机构名称';
comment on column INSURANCEPOLICYPROCESS.SERVICENAME
  is '服务名称';
comment on column INSURANCEPOLICYPROCESS.LOCKSTATUS
  is '加锁状态';
comment on column INSURANCEPOLICYPROCESS.LOCKEDREASON
  is '加锁原因';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTATUS
  is '处理状态';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTARTTIME
  is '处理开始时间';
comment on column INSURANCEPOLICYPROCESS.PROCESSENDTIME
  is '处理结束时间';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTATUSDESC
  is '处理状态描述';
comment on column INSURANCEPOLICYPROCESS.CREATETIME
  is '创建时间';
comment on column INSURANCEPOLICYPROCESS.UPDATETIME
  is '更新时间';
alter table INSURANCEPOLICYPROCESS
  add constraint INSURANCEPOLICYPROCESS_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 4M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PARTYROLEINPOLICY
prompt ================================
prompt
create table PARTYROLEINPOLICY
(
  SERIALNO                  VARCHAR2(32) not null,
  ROLESERIALNO              VARCHAR2(32),
  FIRSTNAME                 VARCHAR2(255),
  LASTNAME                  VARCHAR2(255),
  FULLNAME                  VARCHAR2(100),
  FULLENNAME                VARCHAR2(100),
  IDTYPE                    INTEGER,
  IDNUMBER                  VARCHAR2(50),
  IDEXPDATE                 DATE,
  GENDER                    INTEGER,
  BIRTHDATE                 DATE,
  MARITALSTATUS             INTEGER,
  AGEINDAY                  INTEGER,
  AGE                       INTEGER,
  BIRTHWEIGHT               INTEGER,
  HEIGHT                    INTEGER,
  WEIGHT                    INTEGER,
  MOBILEPHONENUMBER         VARCHAR2(20),
  PHONENUMBER               VARCHAR2(20),
  OFFICEPHONENUMBER         VARCHAR2(20),
  EMAIL                     VARCHAR2(100),
  POSTALCODE                VARCHAR2(20),
  PROVINCE                  VARCHAR2(20),
  CITY                      VARCHAR2(20),
  ADDRESSLINES              VARCHAR2(255),
  OFFICEADDRESS             VARCHAR2(255),
  HOMEADDRESS               VARCHAR2(255),
  ROLEKIND                  VARCHAR2(255),
  ROLEORDER                 INTEGER,
  RELATEDTOAPPLICANT        INTEGER,
  RELATEDTOINSURED          INTEGER,
  MAININSUREDFLAG           INTEGER,
  INSUREDAMOUNTPERCENT      NUMBER(15,2),
  BENEFICIARYTYPE           INTEGER,
  INTERESTPERCENT           NUMBER(15,2),
  CITIZENSHIP               INTEGER,
  OCCUPATIONCLASS           VARCHAR2(20),
  OCCUPATIONCODE            VARCHAR2(20),
  OCCUPATIONDESCRIPTION     VARCHAR2(255),
  ANNUALINCOME              VARCHAR2(20),
  EMPLOYERFULLNAME          VARCHAR2(255),
  DRINKERAMOUNTBYYEAR       INTEGER,
  DRINKERSTATUS             INTEGER,
  DRINKERYEARS              INTEGER,
  SMOKERAMOUNTBYYEAR        INTEGER,
  SMOKERSTATUS              INTEGER,
  SMOKERYEARS               INTEGER,
  ACCEPTSMS                 INTEGER,
  INDICATORMESSAGE          VARCHAR2(255),
  SAMEINDUSTRYINSUREDAMOUNT VARCHAR2(50),
  CUSTOMERID                VARCHAR2(32),
  CUSTOMFLAG                INTEGER,
  PARENTSERIALNO            VARCHAR2(32),
  POLICYSERIALNO            VARCHAR2(32),
  IDVALIDFLAG            	INTEGER,
  IDSTARTDATE            	DATE,
  IDENDDATE            		DATE,
  HOUSEHOLD            		VARCHAR2(200),
  COUNTY            		VARCHAR2(20),
  HOMEZIPCODE            	VARCHAR2(20),
  GRPNAME            		VARCHAR2(200),
  CITIZENSHIPNAME			VARCHAR2(200),
  FAX						VARCHAR2(20),
  LICENSETYPE            	INTEGER,
  LICENSETYPEVIEW			VARCHAR2(20),
  ALIAS						VARCHAR2(20),
  HOBBY						VARCHAR2(20),
  BLOODTYPE					VARCHAR2(20),
  CREATETIME                TIMESTAMP(6),
  UPDATETIME                TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 43M
    minextents 1
    maxextents unlimited
  );
comment on column PARTYROLEINPOLICY.SERIALNO
  is '序号';
comment on column PARTYROLEINPOLICY.ROLESERIALNO
  is '角色编号';
comment on column PARTYROLEINPOLICY.FIRSTNAME
  is '姓';
comment on column PARTYROLEINPOLICY.LASTNAME
  is '名';
comment on column PARTYROLEINPOLICY.FULLNAME
  is '姓名';
comment on column PARTYROLEINPOLICY.FULLENNAME
  is '英文姓名';
comment on column PARTYROLEINPOLICY.IDTYPE
  is '证件类型';
comment on column PARTYROLEINPOLICY.IDNUMBER
  is '证件号码';
comment on column PARTYROLEINPOLICY.IDEXPDATE
  is '证件有效日期';
comment on column PARTYROLEINPOLICY.GENDER
  is '性别';
comment on column PARTYROLEINPOLICY.BIRTHDATE
  is '出生日期';
comment on column PARTYROLEINPOLICY.MARITALSTATUS
  is '婚姻状况';
comment on column PARTYROLEINPOLICY.AGEINDAY
  is '出生天数';
comment on column PARTYROLEINPOLICY.AGE
  is '年龄';
comment on column PARTYROLEINPOLICY.BIRTHWEIGHT
  is '出生体重';
comment on column PARTYROLEINPOLICY.HEIGHT
  is '身高';
comment on column PARTYROLEINPOLICY.WEIGHT
  is '体重';
comment on column PARTYROLEINPOLICY.MOBILEPHONENUMBER
  is '手机电话';
comment on column PARTYROLEINPOLICY.PHONENUMBER
  is '固定电话';
comment on column PARTYROLEINPOLICY.OFFICEPHONENUMBER
  is '办公电话';
comment on column PARTYROLEINPOLICY.EMAIL
  is '电子邮箱';
comment on column PARTYROLEINPOLICY.POSTALCODE
  is '邮政编码';
comment on column PARTYROLEINPOLICY.PROVINCE
  is '省份';
comment on column PARTYROLEINPOLICY.CITY
  is '城市';
comment on column PARTYROLEINPOLICY.ADDRESSLINES
  is '通讯地址';
comment on column PARTYROLEINPOLICY.OFFICEADDRESS
  is '办公地址';
comment on column PARTYROLEINPOLICY.HOMEADDRESS
  is '家庭地址';
comment on column PARTYROLEINPOLICY.ROLEKIND
  is '角色种类';
comment on column PARTYROLEINPOLICY.ROLEORDER
  is '角色顺序';
comment on column PARTYROLEINPOLICY.RELATEDTOAPPLICANT
  is '与投保人关系';
comment on column PARTYROLEINPOLICY.RELATEDTOINSURED
  is '与被保险人关系';
comment on column PARTYROLEINPOLICY.MAININSUREDFLAG
  is '主被保人标志';
comment on column PARTYROLEINPOLICY.INSUREDAMOUNTPERCENT
  is '所占保额百分比';
comment on column PARTYROLEINPOLICY.BENEFICIARYTYPE
  is '受益人类型';
comment on column PARTYROLEINPOLICY.INTERESTPERCENT
  is '受益份额';
comment on column PARTYROLEINPOLICY.CITIZENSHIP
  is '国籍';
comment on column PARTYROLEINPOLICY.OCCUPATIONCLASS
  is '职业类别';
comment on column PARTYROLEINPOLICY.OCCUPATIONCODE
  is '职业代码';
comment on column PARTYROLEINPOLICY.OCCUPATIONDESCRIPTION
  is '工作内容';
comment on column PARTYROLEINPOLICY.ANNUALINCOME
  is '年收入';
comment on column PARTYROLEINPOLICY.EMPLOYERFULLNAME
  is '工作单位名称';
comment on column PARTYROLEINPOLICY.DRINKERAMOUNTBYYEAR
  is '饮酒数量/年';
comment on column PARTYROLEINPOLICY.DRINKERSTATUS
  is '饮酒状况';
comment on column PARTYROLEINPOLICY.DRINKERYEARS
  is '饮酒年限';
comment on column PARTYROLEINPOLICY.SMOKERAMOUNTBYYEAR
  is '吸烟数量/年';
comment on column PARTYROLEINPOLICY.SMOKERSTATUS
  is '吸烟状况';
comment on column PARTYROLEINPOLICY.SMOKERYEARS
  is '吸烟年限';
comment on column PARTYROLEINPOLICY.ACCEPTSMS
  is '是否接受手机短信服务';
comment on column PARTYROLEINPOLICY.INDICATORMESSAGE
  is '告知信息';
comment on column PARTYROLEINPOLICY.SAMEINDUSTRYINSUREDAMOUNT
  is '同业保额';
comment on column PARTYROLEINPOLICY.CUSTOMERID
  is '客户号';
comment on column PARTYROLEINPOLICY.CUSTOMFLAG
  is '旧客户标志';
comment on column PARTYROLEINPOLICY.PARENTSERIALNO
  is '父角色';
comment on column PARTYROLEINPOLICY.POLICYSERIALNO
  is '保单号';
comment on column PARTYROLEINPOLICY.IDVALIDFLAG
  is '证件是否终身有效:1-是;0-否;不填';
comment on column PARTYROLEINPOLICY.IDSTARTDATE
  is '证件起期';
comment on column PARTYROLEINPOLICY.IDENDDATE
  is '证件止期';
comment on column PARTYROLEINPOLICY.HOUSEHOLD
  is '户籍';
comment on column PARTYROLEINPOLICY.COUNTY
  is '县';
comment on column PARTYROLEINPOLICY.HOMEZIPCODE
  is '邮寄邮编';
comment on column PARTYROLEINPOLICY.GRPNAME
  is '公司名称';
comment on column PARTYROLEINPOLICY.CITIZENSHIPNAME
  is '国籍名称';
comment on column PARTYROLEINPOLICY.FAX
  is '传真';
comment on column PARTYROLEINPOLICY.LICENSETYPE
  is '驾照类型';
comment on column PARTYROLEINPOLICY.LICENSETYPEVIEW
  is '驾照类型 显示用';
comment on column PARTYROLEINPOLICY.ALIAS
  is '昵称';
comment on column PARTYROLEINPOLICY.HOBBY
  is '爱好';
comment on column PARTYROLEINPOLICY.BLOODTYPE
  is '血型';
comment on column PARTYROLEINPOLICY.CREATETIME
  is '创建时间';
comment on column PARTYROLEINPOLICY.UPDATETIME
  is '更新时间';
alter table PARTYROLEINPOLICY
  add constraint AK_PARTYROLEINPOLICY_INSURANC primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10M
    minextents 1
    maxextents unlimited
  );
alter table PARTYROLEINPOLICY
  add constraint AK_PARTYROLEINPOLICY_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);
alter table PARTYROLEINPOLICY
  add constraint AK_PARTYROLEINPOLICY_SELF_FK foreign key (PARENTSERIALNO)
  references PARTYROLEINPOLICY (SERIALNO);

prompt
prompt Creating table INSUREDLIABILITY
prompt ===============================
prompt
create table INSUREDLIABILITY
(
  SERIALNO              VARCHAR2(32) not null,
  LIABILITYID           VARCHAR2(20),
  LIABILITYCODE         VARCHAR2(20),
  CORECODE              VARCHAR2(20),
  LIABILITYNAME         VARCHAR2(255),
  PRODUCTCODE           VARCHAR2(20),
  PRODUCTNAME           VARCHAR2(255),
  SUBRISKFLAG           INTEGER,
  GROUPTYPE             INTEGER,
  INSUREDAMOUNT         NUMBER(15,2),
  PREMIUM               NUMBER(15,2),
  DISCOUNTPREMIUM       NUMBER(15,2),
  DISCOUNTTYPECODE      VARCHAR2(20),
  DISCOUNTRATE          NUMBER(15,2),
  BENEFITPERIOD         INTEGER,
  BENEFITPERIODTYPE     INTEGER,
  INSURANCESTARTPERIOD  TIMESTAMP(6),
  INSURANCEENDPERIOD    TIMESTAMP(6),
  PAYMENTMODE           INTEGER,
  PAYMENTDURATION       INTEGER,
  PAYMENTDURATIONMODE   INTEGER,
  DEDUCTIBLES           NUMBER(15,2),
  LOSSRATIO             NUMBER(15,2),
  SEQINDEX              INTEGER,
  INSUREDSERIALNO       VARCHAR2(32),
  PARENTSERIALNO        VARCHAR2(32),
  OPERATORID            VARCHAR2(32),
  CREATETIME            TIMESTAMP(6),
  UPDATETIME            TIMESTAMP(6),
  LIABILITYORDER        INTEGER,
  MAINRISKCODE		    VARCHAR2(20),
  RISKTYPE   			INTEGER,
  RATE   				NUMBER(15,2),
  INCEPTIONDATE   		DATE,
  RANK   				VARCHAR2(20),
  UNITCOUNT   			INTEGER,
  COSTINTV   			VARCHAR2(20),
  COSTDATE   			TIMESTAMP(6),
  SPECCONTENT   		VARCHAR2(200),
  PAYENDYEARFLAG   		INTEGER,
  PAYENDYEAR   			INTEGER,
  GETYEARFLAG   		INTEGER,
  GETYEAR   			INTEGER,
  INSUYEARFLAG   		INTEGER,
  INSUYEAR   			INTEGER,
  GETINTV   			INTEGER,
  GETBANKCODE   		VARCHAR2(20),
  GETBANKACCNO   		VARCHAR2(100),
  GETACCNAME   			VARCHAR2(100),
  GETACCPROVINCE   		VARCHAR2(20),
  GETACCCITY   			VARCHAR2(20),
  GETACCTYPE   			INTEGER,
  AUTOPAYFLAG   		INTEGER,
  BONUSPAYMODE   		INTEGER,
  SUBFLAG   			INTEGER,
  BONUSGETMODE   		INTEGER,
  AUTORNEWFLAG   		INTEGER,
  HEALTHFLAG   			INTEGER,
  FULLBONUSGETMODE   	INTEGER,
  FIRSTRATE   			NUMBER(15,2),
  SURERATE   			NUMBER(15,2),
  MERCHANTPRODUCTCODE   VARCHAR2(20),
  MERCHANTPRODUCTNAME   VARCHAR2(255),
  MERCHANTLIABILITYCODE VARCHAR2(20),
  MERCHANTLIABILITYNAME VARCHAR2(255)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column INSUREDLIABILITY.SERIALNO
  is '序号';
comment on column INSUREDLIABILITY.LIABILITYID
  is '保障/险种ID';
comment on column INSUREDLIABILITY.LIABILITYCODE
  is '保障/险种代码';
comment on column INSUREDLIABILITY.CORECODE
  is '核心保障/险种代码';
comment on column INSUREDLIABILITY.LIABILITYNAME
  is '保障/险种名称';
comment on column INSUREDLIABILITY.PRODUCTCODE
  is '产品代码';
comment on column INSUREDLIABILITY.PRODUCTNAME
  is '产品名称';
comment on column INSUREDLIABILITY.SUBRISKFLAG
  is '主附险标志';
comment on column INSUREDLIABILITY.GROUPTYPE
  is '群体类型';
comment on column INSUREDLIABILITY.INSUREDAMOUNT
  is '保额';
comment on column INSUREDLIABILITY.PREMIUM
  is '保费';
comment on column INSUREDLIABILITY.DISCOUNTPREMIUM
  is '折扣保费';
comment on column INSUREDLIABILITY.DISCOUNTTYPECODE
  is '折扣代码';
comment on column INSUREDLIABILITY.DISCOUNTRATE
  is '折扣系数';
comment on column INSUREDLIABILITY.BENEFITPERIOD
  is '保障年期';
comment on column INSUREDLIABILITY.BENEFITPERIODTYPE
  is '保障年期类型';
comment on column INSUREDLIABILITY.INSURANCESTARTPERIOD
  is '保险起期';
comment on column INSUREDLIABILITY.INSURANCEENDPERIOD
  is '保险止期';
comment on column INSUREDLIABILITY.PAYMENTMODE
  is '缴费方式';
comment on column INSUREDLIABILITY.PAYMENTDURATION
  is '缴费年期';
comment on column INSUREDLIABILITY.PAYMENTDURATIONMODE
  is '缴费年期类型';
comment on column INSUREDLIABILITY.DEDUCTIBLES
  is '免赔额';
comment on column INSUREDLIABILITY.LOSSRATIO
  is '赔付比率';
comment on column INSUREDLIABILITY.SEQINDEX
  is '显示顺序';
comment on column INSUREDLIABILITY.INSUREDSERIALNO
  is '被保人';
comment on column INSUREDLIABILITY.PARENTSERIALNO
  is '父保障/险种代码';
comment on column INSUREDLIABILITY.OPERATORID
  is '操作员';
comment on column INSUREDLIABILITY.CREATETIME
  is '创建时间';
comment on column INSUREDLIABILITY.UPDATETIME
  is '更新时间';
comment on column INSUREDLIABILITY.LIABILITYORDER
  is '保障/险种顺序 ';
comment on column INSUREDLIABILITY.MAINRISKCODE
  is '主险险种代码';
comment on column INSUREDLIABILITY.RISKTYPE
  is '险种类型';
comment on column INSUREDLIABILITY.RATE
  is '费率';
comment on column INSUREDLIABILITY.INCEPTIONDATE
  is '生效日';
comment on column INSUREDLIABILITY.RANK
  is '档次';
comment on column INSUREDLIABILITY.UNITCOUNT
  is '份数';
comment on column INSUREDLIABILITY.COSTINTV
  is '扣款间隔';
comment on column INSUREDLIABILITY.COSTDATE
  is '扣款时间';
comment on column INSUREDLIABILITY.SPECCONTENT
  is '特别约定';
comment on column INSUREDLIABILITY.PAYENDYEARFLAG
  is '缴费年期年龄标志';
comment on column INSUREDLIABILITY.PAYENDYEAR
  is '缴费年期年龄';
comment on column INSUREDLIABILITY.GETYEARFLAG
  is '领取年龄年期标志';
comment on column INSUREDLIABILITY.GETYEAR
  is '领取年龄';
comment on column INSUREDLIABILITY.INSUYEARFLAG
  is '保险年期年龄标志';
comment on column INSUREDLIABILITY.INSUYEAR
  is '保险年期年龄';
comment on column INSUREDLIABILITY.GETINTV
  is '领取方式';
comment on column INSUREDLIABILITY.GETBANKCODE
  is '领取银行编码';
comment on column INSUREDLIABILITY.GETBANKACCNO
  is '领取银行账户';
comment on column INSUREDLIABILITY.GETACCNAME
  is '领取银行户名';
comment on column INSUREDLIABILITY.GETACCPROVINCE
  is '领取银行省编码';
comment on column INSUREDLIABILITY.GETACCCITY
  is '领取银行市编码';
comment on column INSUREDLIABILITY.GETACCTYPE
  is '领取银行卡折类型';
comment on column INSUREDLIABILITY.AUTOPAYFLAG
  is '垫交标志';
comment on column INSUREDLIABILITY.BONUSPAYMODE
  is '红利分配标记';
comment on column INSUREDLIABILITY.SUBFLAG
  is '减额交清标志';
comment on column INSUREDLIABILITY.BONUSGETMODE
  is '红利领取方式';
comment on column INSUREDLIABILITY.AUTORNEWFLAG
  is '自动续交标志';
comment on column INSUREDLIABILITY.HEALTHFLAG
  is '健康告之标志';
comment on column INSUREDLIABILITY.FULLBONUSGETMODE
  is '满期领取金领取方式';
comment on column INSUREDLIABILITY.FIRSTRATE
  is '初始费用率';
comment on column INSUREDLIABILITY.SURERATE
  is '保证利率';
comment on column INSUREDLIABILITY.MERCHANTPRODUCTCODE
  is '商家产品代码 ';
comment on column INSUREDLIABILITY.MERCHANTPRODUCTNAME
  is '商家产品名称';
comment on column INSUREDLIABILITY.MERCHANTLIABILITYCODE
  is '商家险种代码';
comment on column INSUREDLIABILITY.MERCHANTLIABILITYNAME
  is '商家险种代码';
alter table INSUREDLIABILITY
  add constraint INSUREDLIABILITY_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table INSUREDLIABILITY
  add constraint INSUREDLIABILITY_RT_INSURED_FK foreign key (INSUREDSERIALNO)
  references PARTYROLEINPOLICY (SERIALNO);
alter table INSUREDLIABILITY
  add constraint INSUREDLIABILITY_RT_SF_FK foreign key (PARENTSERIALNO)
  references INSUREDLIABILITY (SERIALNO);

prompt
prompt Creating table INSUREINFORMBOOK
prompt ===============================
prompt
create table INSUREINFORMBOOK
(
  SERIALNO       VARCHAR2(32) not null,
  INFORMCODE     VARCHAR2(100),
  INFORMTYPE     INTEGER,
  INFORMTYPENAME VARCHAR2(20),
  INFORMORDER    INTEGER,
  INFORMCONTENT  VARCHAR2(1000),
  POLICYSERIALNO VARCHAR2(32) not null,
  TELLVERSION  	 VARCHAR2(10),
  TELLREMARK  	 VARCHAR2(1000),
  CREATETIME     TIMESTAMP(6),
  UPDATETIME     TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128M
    minextents 1
    maxextents unlimited
  );
comment on column INSUREINFORMBOOK.SERIALNO
  is '序号';
comment on column INSUREINFORMBOOK.INFORMCODE
  is '告知代码';
comment on column INSUREINFORMBOOK.INFORMTYPE
  is '告知类型';
comment on column INSUREINFORMBOOK.INFORMTYPENAME
  is '告知类型名称';
comment on column INSUREINFORMBOOK.INFORMORDER
  is '告知顺序';
comment on column INSUREINFORMBOOK.INFORMCONTENT
  is '告知内容';
comment on column INSUREINFORMBOOK.POLICYSERIALNO
  is '保单';
comment on column INSUREINFORMBOOK.TELLVERSION
  is '告知版别';
comment on column INSUREINFORMBOOK.TELLREMARK
  is '告知备注';
comment on column INSUREINFORMBOOK.CREATETIME
  is '创建时间';
comment on column INSUREINFORMBOOK.UPDATETIME
  is '更新时间 ';
alter table INSUREINFORMBOOK
  add constraint INSUREINFORMBOOK_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 21M
    minextents 1
    maxextents unlimited
  );
alter table INSUREINFORMBOOK
  add constraint INSUREINFORMBOOK_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);

prompt
prompt Creating table INVOICE
prompt ======================
prompt
create table INVOICE
(
  SERIALNO          VARCHAR2(32) not null,
  INVOICETYPE       INTEGER,
  INVOICETITLE      VARCHAR2(50),
  INVOINCECONTENT   VARCHAR2(2),
  COMPANYNAME       VARCHAR2(255),
  REGISTEREDADDRESS VARCHAR2(255),
  REGISTEREDPHONE   VARCHAR2(20),
  OPENINGBANK       VARCHAR2(255),
  TAXREGISTRYNO     VARCHAR2(50),
  INVOICECODE       VARCHAR2(50),
  INVOICENO         VARCHAR2(50),
  PRINTNO           VARCHAR2(50),
  PRINTERMACHINENO  VARCHAR2(50),
  TAXCONTROLCODE    VARCHAR2(50),
  CURRENCYSYMBOL    VARCHAR2(10),
  TOTALINFIGURES    NUMBER(15,2),
  TOTALINLETTERS    VARCHAR2(100),
  PAYEE             VARCHAR2(100),
  PAYER             VARCHAR2(100),
  BANKACCOUNT       VARCHAR2(100),
  CASHIER           TIMESTAMP(6),
  PAYEESEAL         VARCHAR2(100),
  POLICYSERIALNO    VARCHAR2(32) not null,
  CREATETIME        TIMESTAMP(6),
  UPDATETIME        TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column INVOICE.SERIALNO
  is '序号';
comment on column INVOICE.INVOICETYPE
  is '发票类型';
comment on column INVOICE.INVOICETITLE
  is '发票抬头';
comment on column INVOICE.INVOINCECONTENT
  is '发票内容';
comment on column INVOICE.COMPANYNAME
  is '单位名称';
comment on column INVOICE.REGISTEREDADDRESS
  is '注册地址';
comment on column INVOICE.REGISTEREDPHONE
  is '注册电话';
comment on column INVOICE.OPENINGBANK
  is '开户银行';
comment on column INVOICE.TAXREGISTRYNO
  is '税号';
comment on column INVOICE.INVOICECODE
  is '发票代码';
comment on column INVOICE.INVOICENO
  is '发票号';
comment on column INVOICE.PRINTNO
  is '打印机号码';
comment on column INVOICE.PRINTERMACHINENO
  is '打印机编号';
comment on column INVOICE.TAXCONTROLCODE
  is '税控码';
comment on column INVOICE.CURRENCYSYMBOL
  is '货币符号';
comment on column INVOICE.TOTALINFIGURES
  is '小写合计';
comment on column INVOICE.TOTALINLETTERS
  is '大写合计';
comment on column INVOICE.PAYEE
  is '收款单位';
comment on column INVOICE.PAYER
  is '付款单位(个人)';
comment on column INVOICE.BANKACCOUNT
  is '银行帐户';
comment on column INVOICE.CASHIER
  is '收款员';
comment on column INVOICE.PAYEESEAL
  is '收款单位印章';
comment on column INVOICE.POLICYSERIALNO
  is '保单';
comment on column INVOICE.CREATETIME
  is '创建时间';
comment on column INVOICE.UPDATETIME
  is '更新时间';
alter table INVOICE
  add constraint AK_INVOICE_PK_INVOICE primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table INVOICE
  add constraint AK_INVOICE_RT_UK unique (POLICYSERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table INVOICE
  add constraint AK_INVOICE_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);

prompt
prompt Creating table INVOICEITEM
prompt ==========================
prompt
create table INVOICEITEM
(
  SERIALNO        CHAR(32) not null,
  ITEM            CHAR(100),
  UNITPRICE       NUMBER(15,2),
  AMOUNT          NUMBER(15,2),
  AMOUNTCHANGED   NUMBER(15,2),
  INVOICESERIALNO VARCHAR2(32),
  CREATETIME      TIMESTAMP(6),
  UPDATETIME      TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column INVOICEITEM.SERIALNO
  is '序号';
comment on column INVOICEITEM.ITEM
  is '项目名';
comment on column INVOICEITEM.UNITPRICE
  is '单价';
comment on column INVOICEITEM.AMOUNT
  is '数量';
comment on column INVOICEITEM.AMOUNTCHANGED
  is '金额';
comment on column INVOICEITEM.INVOICESERIALNO
  is '发票';
comment on column INVOICEITEM.CREATETIME
  is '创建时间';
comment on column INVOICEITEM.UPDATETIME
  is '更新时间 ';
alter table INVOICEITEM
  add constraint INVOICEITEM_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table INVOICEITEM
  add constraint INVITEM_RT_INV_FK foreign key (INVOICESERIALNO)
  references INVOICE (SERIALNO);

prompt
prompt Creating table ORDERFORM
prompt ========================
prompt
create table ORDERFORM
(
  SERIALNO             VARCHAR2(32) not null,
  ORDERSERIALNUMBER    VARCHAR2(50),
  MERCHANTORDERNUMBER  VARCHAR2(50),
  TRANSSERIALNUMBER    VARCHAR2(32),
  PRODUCTCODE          VARCHAR2(50),
  PRODUCTNAME          VARCHAR2(100),
  PRODUCTNUMBER        INTEGER,
  PRODUCTDESC          VARCHAR2(500),
  PAYERCONTACTTYPE     INTEGER,
  PAYERCONTACT         VARCHAR2(255),
  PAYERIP              VARCHAR2(50),
  PERSONALUSERSERIALNO VARCHAR2(32),
  CUSTOMERID           VARCHAR2(32),
  ORDERTYPE            INTEGER,
  ORDERSTATUS          INTEGER,
  ORDERSTATUSNAME      VARCHAR2(50),
  ORDERSTATUSDESC      VARCHAR2(1000),
  ORDERAMOUNT          NUMBER(15,2),
  PAYMETHOD            INTEGER,
  PAYTIME              TIMESTAMP(6),
  PAYMENTMETHOD        INTEGER,
  PAYSTATUS            INTEGER,
  PAYSTATUSNAME        VARCHAR2(100),
  PAYSTATUSDESC        VARCHAR2(255),
  SUBMITTIME           TIMESTAMP(6),
  GATEWAYORDERNO       VARCHAR2(50),
  BANKORDERNO          VARCHAR2(50),
  REFUNDMENTFLAG       INTEGER,
  OPERATORID           VARCHAR2(32),
  POLICYSERIALNO       VARCHAR2(32) not null,
  CREATETIME           TIMESTAMP(6),
  UPDATETIME           TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column ORDERFORM.SERIALNO
  is '序号';
comment on column ORDERFORM.ORDERSERIALNUMBER
  is '订单号';
comment on column ORDERFORM.MERCHANTORDERNUMBER
  is '合作机构订单号（淘宝订单号）';
comment on column ORDERFORM.TRANSSERIALNUMBER
  is '交易流水号';
comment on column ORDERFORM.PRODUCTCODE
  is '产品编码';
comment on column ORDERFORM.PRODUCTNAME
  is '产品名称';
comment on column ORDERFORM.PRODUCTNUMBER
  is '商品数量';
comment on column ORDERFORM.PRODUCTDESC
  is '产品描述';
comment on column ORDERFORM.PAYERCONTACTTYPE
  is '支付人联系方式类型 (1代表邮件 2手机)';
comment on column ORDERFORM.PAYERCONTACT
  is '支付人联系方式';
comment on column ORDERFORM.PAYERIP
  is '支付人IP';
comment on column ORDERFORM.PERSONALUSERSERIALNO
  is '个人客户';
comment on column ORDERFORM.CUSTOMERID
  is '客户号ID';
comment on column ORDERFORM.ORDERTYPE
  is '订单类型';
comment on column ORDERFORM.ORDERSTATUS
  is '订单状态';
comment on column ORDERFORM.ORDERSTATUSNAME
  is '订单状态名称';
comment on column ORDERFORM.ORDERSTATUSDESC
  is '订单状态描述';
comment on column ORDERFORM.ORDERAMOUNT
  is '订单金额';
comment on column ORDERFORM.PAYMETHOD
  is '支付方式';
comment on column ORDERFORM.PAYTIME
  is '支付时间';
comment on column ORDERFORM.PAYMENTMETHOD
  is '支付方式';
comment on column ORDERFORM.PAYSTATUS
  is '支付状态';
comment on column ORDERFORM.PAYSTATUSNAME
  is '支付状态名称';
comment on column ORDERFORM.PAYSTATUSDESC
  is '支付状态描述';
comment on column ORDERFORM.SUBMITTIME
  is '提交时间';
comment on column ORDERFORM.GATEWAYORDERNO
  is '网关订单号';
comment on column ORDERFORM.BANKORDERNO
  is '银行订单号';
comment on column ORDERFORM.REFUNDMENTFLAG
  is '退单标示';
comment on column ORDERFORM.POLICYSERIALNO
  is ' 保单';
comment on column ORDERFORM.CREATETIME
  is '创建时间';
comment on column ORDERFORM.UPDATETIME
  is '更新时间';
alter table ORDERFORM
  add constraint ORDERFORM_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table ORDERFORM
  add constraint ORDERFORM_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);

prompt
prompt Creating table PARTNERINSTITUTION
prompt =================================
prompt
create table PARTNERINSTITUTION
(
  SERIALNO              VARCHAR2(32) not null,
  INSTITUTIONCODE       VARCHAR2(20),
  INSTITUTIONNAME       VARCHAR2(255),
  INSTITUTIONENNAME     VARCHAR2(255),
  INSTITUTIONPINYINNAME VARCHAR2(255),
  INSTITUTIONPROPERTY   VARCHAR2(2),
  INSTITUTIONWEBSITE    VARCHAR2(100),
  INSTITUTIONPROFILE    VARCHAR2(500),
  INSTITUTIONDESC       VARCHAR2(500),
  SIGNEDDATE            DATE,
  TERMINATIONDATE       DATE,
  OPERATORID            TIMESTAMP(6),
  CREATETIME            TIMESTAMP(6),
  UPDATETIME            TIMESTAMP(6),
  REMARK                VARCHAR2(500)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column PARTNERINSTITUTION.SERIALNO
  is '序号';
comment on column PARTNERINSTITUTION.INSTITUTIONCODE
  is '合作机构代码';
comment on column PARTNERINSTITUTION.INSTITUTIONNAME
  is '合作机构名称';
comment on column PARTNERINSTITUTION.INSTITUTIONENNAME
  is '合作机构英文名称';
comment on column PARTNERINSTITUTION.INSTITUTIONPINYINNAME
  is '合作机构拼音名称';
comment on column PARTNERINSTITUTION.INSTITUTIONPROPERTY
  is '合作机构性质';
comment on column PARTNERINSTITUTION.INSTITUTIONWEBSITE
  is '合作机构网站';
comment on column PARTNERINSTITUTION.INSTITUTIONPROFILE
  is '合作机构简介';
comment on column PARTNERINSTITUTION.INSTITUTIONDESC
  is '合作机构描述';
comment on column PARTNERINSTITUTION.SIGNEDDATE
  is '签约日期';
comment on column PARTNERINSTITUTION.TERMINATIONDATE
  is '解约日期';
comment on column PARTNERINSTITUTION.OPERATORID
  is '操作员ID';
comment on column PARTNERINSTITUTION.CREATETIME
  is '创建时间';
comment on column PARTNERINSTITUTION.UPDATETIME
  is '更新时间';
comment on column PARTNERINSTITUTION.REMARK
  is '备注';

prompt
prompt Creating table PAYMENTACCOUNT
prompt =============================
prompt
create table PAYMENTACCOUNT
(
  SERIALNO             VARCHAR2(32) not null,
  PAYORDERSERIALNUMBER VARCHAR2(50),
  ACCOUNTNUMBER        VARCHAR2(50),
  ACCOUNTNUMBERCONFIRM VARCHAR2(50),
  ACCTHOLDERID         VARCHAR2(50),
  ACCTHOLDERNAME       VARCHAR2(100),
  ACCTIDNUMBER         VARCHAR2(100),
  ACCTMOBILEPHONE      VARCHAR2(100),
  CARDORBOOK           INTEGER,
  CURRENCYTYPECODE     VARCHAR2(20),
  BANKCARDCVTWO        VARCHAR2(50),
  CARDEFFECTIVEDATE    VARCHAR2(50),
  BANKACCTTYPE         INTEGER,
  BANKCODE             VARCHAR2(20),
  BANKNAME             VARCHAR2(100),
  BANKBRANCHCODE       VARCHAR2(20),
  BANKBRANCHNAME       VARCHAR2(100),
  PAYAMOUNT            NUMBER(15,2),
  PAYTIME              TIMESTAMP(6),
  CHECKPAYNUMBER       VARCHAR2(50),
  ACCOUNTTIME          TIMESTAMP(6),
  ACCPROVINCE          VARCHAR2(20),
  ACCCITY              VARCHAR2(20),
  SECBANKCODE          VARCHAR2(20),
  SECBANKACCNO         VARCHAR2(20),
  SECACCNAME           VARCHAR2(100),
  SECACCPROVINCE       VARCHAR2(20),
  SECACCCITY           VARCHAR2(20),
  SECACCTYPE           INTEGER,
  OPERATORID           VARCHAR2(32),
  POLICYSERIALNO       VARCHAR2(32),
  CREATETIME           TIMESTAMP(6),
  UPDATETIME           TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 21M
    minextents 1
    maxextents unlimited
  );
comment on column PAYMENTACCOUNT.SERIALNO
  is '序号';
comment on column PAYMENTACCOUNT.PAYORDERSERIALNUMBER
  is '交费订单号';
comment on column PAYMENTACCOUNT.ACCOUNTNUMBER
  is '缴费账号';
comment on column PAYMENTACCOUNT.ACCOUNTNUMBERCONFIRM
  is '缴费确认账号';
comment on column PAYMENTACCOUNT.ACCTHOLDERID
  is '帐号持有人Id';
comment on column PAYMENTACCOUNT.ACCTHOLDERNAME
  is '帐号持有人姓名';
comment on column PAYMENTACCOUNT.ACCTIDNUMBER
  is '帐号持有人身份证号码';
comment on column PAYMENTACCOUNT.ACCTMOBILEPHONE
  is '帐号持有人手机号';
comment on column PAYMENTACCOUNT.CARDORBOOK
  is '卡/存折';
comment on column PAYMENTACCOUNT.CURRENCYTYPECODE
  is '银行帐号币种';
comment on column PAYMENTACCOUNT.BANKCARDCVTWO
  is '信用卡校验码';
comment on column PAYMENTACCOUNT.CARDEFFECTIVEDATE
  is '卡有效期';
comment on column PAYMENTACCOUNT.BANKACCTTYPE
  is '银行帐号类型';
comment on column PAYMENTACCOUNT.BANKCODE
  is '银行代码';
comment on column PAYMENTACCOUNT.BANKNAME
  is '银行名称';
comment on column PAYMENTACCOUNT.BANKBRANCHCODE
  is '分行/支行代码';
comment on column PAYMENTACCOUNT.BANKBRANCHNAME
  is '分行/支行名称';
comment on column PAYMENTACCOUNT.PAYAMOUNT
  is '支付金额';
comment on column PAYMENTACCOUNT.PAYTIME
  is '支付时间';
comment on column PAYMENTACCOUNT.CHECKPAYNUMBER
  is '对账单号';
comment on column PAYMENTACCOUNT.ACCOUNTTIME
  is '对账时间';
comment on column PAYMENTACCOUNT.ACCPROVINCE
  is '银行省编码';
comment on column PAYMENTACCOUNT.ACCCITY
  is '银行市编码';
comment on column PAYMENTACCOUNT.SECBANKCODE
  is '续期银行账户';
comment on column PAYMENTACCOUNT.SECBANKACCNO
  is '续期银行编码';
comment on column PAYMENTACCOUNT.SECACCNAME
  is '续期账户姓名';
comment on column PAYMENTACCOUNT.SECACCPROVINCE
  is '续期银行省编码';
comment on column PAYMENTACCOUNT.SECACCCITY
  is '续期银行市编码';
comment on column PAYMENTACCOUNT.SECACCTYPE
  is '续期银行卡折类型';
comment on column PAYMENTACCOUNT.OPERATORID
  is '操作员';
comment on column PAYMENTACCOUNT.POLICYSERIALNO
  is '保单';
comment on column PAYMENTACCOUNT.CREATETIME
  is '创建时间';
comment on column PAYMENTACCOUNT.UPDATETIME
  is '更新时间';
alter table PAYMENTACCOUNT
  add constraint PAYMENTACCOUNT_PK primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 5M
    minextents 1
    maxextents unlimited
  );
alter table PAYMENTACCOUNT
  add constraint PAYMENTACCOUNT_RT_UK unique (POLICYSERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 6M
    minextents 1
    maxextents unlimited
  );
alter table PAYMENTACCOUNT
  add constraint PAYMENTACCOUNT_RT_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);

prompt
prompt Creating table BINDPOLICY
prompt =========================
prompt
create table BINDPOLICY
(
  SERIALNO           VARCHAR2(32) not null,
  CUSTOMERID         VARCHAR2(32) not null,
  POLICYSERIALNUMBER VARCHAR2(50) not null
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table BINDPOLICY
  is '保单绑定表';
comment on column BINDPOLICY.SERIALNO
  is '主键';
comment on column BINDPOLICY.CUSTOMERID
  is '客户号';
comment on column BINDPOLICY.POLICYSERIALNUMBER
  is '保单号';
alter table BINDPOLICY
  add constraint PK_BINDPOLICY primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table TOPINSURED
prompt ================================
prompt
create table TOPINSURED
(
  SERIALNO                  VARCHAR2(32) not null,
  ROLESERIALNO              VARCHAR2(32),
  FIRSTNAME                 VARCHAR2(255),
  LASTNAME                  VARCHAR2(255),
  FULLNAME                  VARCHAR2(100),
  FULLENNAME                VARCHAR2(100),
  IDTYPE                    INTEGER,
  IDNUMBER                  VARCHAR2(50),
  IDEXPDATE                 DATE,
  GENDER                    INTEGER,
  BIRTHDATE                 DATE,
  MARITALSTATUS             INTEGER,
  AGEINDAY                  INTEGER,
  AGE                       INTEGER,
  BIRTHWEIGHT               INTEGER,
  HEIGHT                    INTEGER,
  WEIGHT                    INTEGER,
  MOBILEPHONENUMBER         VARCHAR2(20),
  PHONENUMBER               VARCHAR2(20),
  OFFICEPHONENUMBER         VARCHAR2(20),
  EMAIL                     VARCHAR2(100),
  POSTALCODE                VARCHAR2(20),
  PROVINCE                  VARCHAR2(20),
  CITY                      VARCHAR2(20),
  ADDRESSLINES              VARCHAR2(255),
  OFFICEADDRESS             VARCHAR2(255),
  HOMEADDRESS               VARCHAR2(255),
  ROLEKIND                  VARCHAR2(255),
  ROLEORDER                 INTEGER,
  RELATEDTOAPPLICANT        INTEGER,
  INSUREDAMOUNTPERCENT      NUMBER(15,2),
  CITIZENSHIP               INTEGER,
  OCCUPATIONCLASS           VARCHAR2(20),
  OCCUPATIONCODE            VARCHAR2(20),
  OCCUPATIONDESCRIPTION     VARCHAR2(255),
  ANNUALINCOME              VARCHAR2(20),
  EMPLOYERFULLNAME          VARCHAR2(255),
  DRINKERAMOUNTBYYEAR       INTEGER,
  DRINKERSTATUS             INTEGER,
  DRINKERYEARS              INTEGER,
  SMOKERAMOUNTBYYEAR        INTEGER,
  SMOKERSTATUS              INTEGER,
  SMOKERYEARS               INTEGER,
  ACCEPTSMS                 INTEGER,
  INDICATORMESSAGE          VARCHAR2(255),
  SAMEINDUSTRYINSUREDAMOUNT VARCHAR2(50),
  CUSTOMERID                VARCHAR2(32),
  CUSTOMFLAG                INTEGER,
  PARENTSERIALNO            VARCHAR2(32),
  POLICYSERIALNO            VARCHAR2(32),
  IDVALIDFLAG            	INTEGER,
  IDSTARTDATE            	DATE,
  IDENDDATE            		DATE,
  HOUSEHOLD            		VARCHAR2(200),
  COUNTY            		VARCHAR2(20),
  HOMEZIPCODE            	VARCHAR2(20),
  GRPNAME            		VARCHAR2(200),
  CITIZENSHIPNAME			VARCHAR2(200),
  FAX						VARCHAR2(20),
  LICENSETYPE            	INTEGER,
  LICENSETYPEVIEW			VARCHAR2(20),
  ALIAS						VARCHAR2(20),
  HOBBY						VARCHAR2(20),
  BLOODTYPE					VARCHAR2(20),
  CREATETIME                TIMESTAMP(6),
  UPDATETIME                TIMESTAMP(6)
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 43M
    minextents 1
    maxextents unlimited
  );
comment on column TOPINSURED.SERIALNO
  is '序号';
comment on column TOPINSURED.ROLESERIALNO
  is '角色编号';
comment on column TOPINSURED.FIRSTNAME
  is '姓';
comment on column TOPINSURED.LASTNAME
  is '名';
comment on column TOPINSURED.FULLNAME
  is '姓名';
comment on column TOPINSURED.FULLENNAME
  is '英文姓名';
comment on column TOPINSURED.IDTYPE
  is '证件类型';
comment on column TOPINSURED.IDNUMBER
  is '证件号码';
comment on column TOPINSURED.IDEXPDATE
  is '证件有效日期';
comment on column TOPINSURED.GENDER
  is '性别';
comment on column TOPINSURED.BIRTHDATE
  is '出生日期';
comment on column TOPINSURED.MARITALSTATUS
  is '婚姻状况';
comment on column TOPINSURED.AGEINDAY
  is '出生天数';
comment on column TOPINSURED.AGE
  is '年龄';
comment on column TOPINSURED.BIRTHWEIGHT
  is '出生体重';
comment on column TOPINSURED.HEIGHT
  is '身高';
comment on column TOPINSURED.WEIGHT
  is '体重';
comment on column TOPINSURED.MOBILEPHONENUMBER
  is '手机电话';
comment on column TOPINSURED.PHONENUMBER
  is '固定电话';
comment on column TOPINSURED.OFFICEPHONENUMBER
  is '办公电话';
comment on column TOPINSURED.EMAIL
  is '电子邮箱';
comment on column TOPINSURED.POSTALCODE
  is '邮政编码';
comment on column TOPINSURED.PROVINCE
  is '省份';
comment on column TOPINSURED.CITY
  is '城市';
comment on column TOPINSURED.ADDRESSLINES
  is '通讯地址';
comment on column TOPINSURED.OFFICEADDRESS
  is '办公地址';
comment on column TOPINSURED.HOMEADDRESS
  is '家庭地址';
comment on column TOPINSURED.ROLEKIND
  is '角色种类';
comment on column TOPINSURED.ROLEORDER
  is '角色顺序';
comment on column TOPINSURED.RELATEDTOAPPLICANT
  is '与投保人关系';
comment on column TOPINSURED.INSUREDAMOUNTPERCENT
  is '所占保额百分比';
comment on column TOPINSURED.CITIZENSHIP
  is '国籍';
comment on column TOPINSURED.OCCUPATIONCLASS
  is '职业类别';
comment on column TOPINSURED.OCCUPATIONCODE
  is '职业代码';
comment on column TOPINSURED.OCCUPATIONDESCRIPTION
  is '工作内容';
comment on column TOPINSURED.ANNUALINCOME
  is '年收入';
comment on column TOPINSURED.EMPLOYERFULLNAME
  is '工作单位名称';
comment on column TOPINSURED.DRINKERAMOUNTBYYEAR
  is '饮酒数量/年';
comment on column TOPINSURED.DRINKERSTATUS
  is '饮酒状况';
comment on column TOPINSURED.DRINKERYEARS
  is '饮酒年限';
comment on column TOPINSURED.SMOKERAMOUNTBYYEAR
  is '吸烟数量/年';
comment on column TOPINSURED.SMOKERSTATUS
  is '吸烟状况';
comment on column TOPINSURED.SMOKERYEARS
  is '吸烟年限';
comment on column TOPINSURED.ACCEPTSMS
  is '是否接受手机短信服务';
comment on column TOPINSURED.INDICATORMESSAGE
  is '告知信息';
comment on column TOPINSURED.SAMEINDUSTRYINSUREDAMOUNT
  is '同业保额';
comment on column TOPINSURED.CUSTOMERID
  is '客户号';
comment on column TOPINSURED.CUSTOMFLAG
  is '旧客户标志';
comment on column TOPINSURED.PARENTSERIALNO
  is '父角色';
comment on column TOPINSURED.POLICYSERIALNO
  is '保单号';
comment on column TOPINSURED.IDVALIDFLAG
  is '证件是否终身有效:1-是;0-否;不填';
comment on column TOPINSURED.IDSTARTDATE
  is '证件起期';
comment on column TOPINSURED.IDENDDATE
  is '证件止期';
comment on column TOPINSURED.HOUSEHOLD
  is '户籍';
comment on column TOPINSURED.COUNTY
  is '县';
comment on column TOPINSURED.HOMEZIPCODE
  is '邮寄邮编';
comment on column TOPINSURED.GRPNAME
  is '公司名称';
comment on column TOPINSURED.CITIZENSHIPNAME
  is '国籍名称';
comment on column TOPINSURED.FAX
  is '传真';
comment on column TOPINSURED.LICENSETYPE
  is '驾照类型';
comment on column TOPINSURED.LICENSETYPEVIEW
  is '驾照类型 显示用';
comment on column TOPINSURED.ALIAS
  is '昵称';
comment on column TOPINSURED.HOBBY
  is '爱好';
comment on column TOPINSURED.BLOODTYPE
  is '血型';
comment on column TOPINSURED.CREATETIME
  is '创建时间';
comment on column TOPINSURED.UPDATETIME
  is '更新时间';
alter table TOPINSURED
  add constraint AK_TOPINSURED_INSURANC primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10M
    minextents 1
    maxextents unlimited
  );
alter table TOPINSURED
  add constraint AK_TOPINSURED_PL_FK foreign key (POLICYSERIALNO)
  references INSURANCEPOLICY (SERIALNO);
alter table TOPINSURED
  add constraint AK_TOPINSURED_SELF_FK foreign key (PARENTSERIALNO)
  references TOPINSURED (SERIALNO);


prompt
prompt Creating table HOSPITALMANAGE
prompt =============================
prompt
create table HOSPITALMANAGE
(
  SERIALNO VARCHAR2(32) not null,
  PROVINCE VARCHAR2(32) not null,
  CITY     VARCHAR2(32) not null,
  HOSNAME  VARCHAR2(100) not null,
  HOSADDR  VARCHAR2(200) not null
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table HOSPITALMANAGE
  is '定点医院管理';
comment on column HOSPITALMANAGE.SERIALNO
  is '主键';
comment on column HOSPITALMANAGE.PROVINCE
  is '中支';
comment on column HOSPITALMANAGE.CITY
  is '机构';
comment on column HOSPITALMANAGE.HOSNAME
  is '医院名称';
comment on column HOSPITALMANAGE.HOSADDR
  is '医院地址';
alter table HOSPITALMANAGE
  add constraint PK_HOSPITALMANAGE primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255;


prompt
prompt Creating table GE_UNIVERSAL_RATE
prompt ================================
prompt
create table GE_UNIVERSAL_RATE
(
  SERIALNO VARCHAR2(32) not null,
  RISKNAME VARCHAR2(80) not null,
  CULDATE  DATE not null,
  DATERATE NUMBER(12,10) not null,
  YEARRATE NUMBER(12,10) not null
)
tablespace XTMALL
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table GE_UNIVERSAL_RATE
  is 'tabledesc';
comment on column GE_UNIVERSAL_RATE.SERIALNO
  is '主键';
comment on column GE_UNIVERSAL_RATE.RISKNAME
  is '险种名称';
comment on column GE_UNIVERSAL_RATE.CULDATE
  is '结算日期';
comment on column GE_UNIVERSAL_RATE.DATERATE
  is '结算日利率';
comment on column GE_UNIVERSAL_RATE.YEARRATE
  is '折合年结算利率';
alter table GE_UNIVERSAL_RATE
  add constraint PK_GE_UNIVERSAL_RATE primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255;

spool off

--2013年10月22日 09:31:10 提交
--用户密码输入错误次数，超过三次之后，锁定帐号
alter table ge_user_personal add loginFailedCount integer default 0;
--当前帐号是否已被锁定
alter table ge_user_personal add lockUserAccount varchar(1) default 'N';
--帐号锁定时间
alter table ge_user_personal add lockTime timestamp;


