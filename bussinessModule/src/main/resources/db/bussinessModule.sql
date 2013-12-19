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
  is '���';
comment on column ABNORMALORDERFORM.ORDERSERIALNUMBER
  is '������';
comment on column ABNORMALORDERFORM.APPLICATIONNUMBER
  is 'Ͷ������';
comment on column ABNORMALORDERFORM.ORDERSTATUS
  is '����״̬';
comment on column ABNORMALORDERFORM.MANUALINSUREDSTATUS
  is '�ֶ��б�״̬';
comment on column ABNORMALORDERFORM.PAYMENTMETHOD
  is '֧����ʽ';
comment on column ABNORMALORDERFORM.PAYSTATUS
  is '֧��״̬';
comment on column ABNORMALORDERFORM.OPERATORID
  is '����Ա';
comment on column ABNORMALORDERFORM.EXCEPTIONCODE
  is '�쳣����';
comment on column ABNORMALORDERFORM.EXCEPTIONDESCRIPTION
  is '�쳣����';
comment on column ABNORMALORDERFORM.EXCEPTIONSTACK
  is '�쳣��ջ';
comment on column ABNORMALORDERFORM.SENDEMAILTIME
  is '�����ʼ�ʱ��';
comment on column ABNORMALORDERFORM.CREATETIME
  is '����ʱ��';
comment on column ABNORMALORDERFORM.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column ADDRESSEE.CONSIGNEENAME
  is '�ռ�������';
comment on column ADDRESSEE.TELEPHONE
  is '��ϵ�绰';
comment on column ADDRESSEE.FIXEDPHONE
  is '�̶��绰';
comment on column ADDRESSEE.PROVINCE
  is 'ʡ';
comment on column ADDRESSEE.CITY
  is '��';
comment on column ADDRESSEE.COUNTY
  is '��/��';
comment on column ADDRESSEE.CONSIGNEEADDRESS
  is '�ռ���ַ';
comment on column ADDRESSEE.ZIPCODE
  is '��������';
comment on column ADDRESSEE.EMAIL
  is '��������';
comment on column ADDRESSEE.POLICYSERIALNO
  is '����';
comment on column ADDRESSEE.CREATETIME
  is '����ʱ��';
comment on column ADDRESSEE.UPDATETIME
  is '����ʱ��';
comment on column ADDRESSEE.REMARK
  is '��ע';
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
  is '��ţ��߼�����UUID���ɲ��ԣ�';
comment on column ELECTRONICPOLICY.COM_CODE
  is '�ֹ�˾��';
comment on column ELECTRONICPOLICY.POLICY_NO
  is '������';
comment on column ELECTRONICPOLICY.ORDER_NO
  is '������';
comment on column ELECTRONICPOLICY.VAILD_FLAG
  is '��Ч��ǣ�Ĭ��Ϊ1����Ч��2Ϊ���ϴ�CM��3Ϊ';
comment on column ELECTRONICPOLICY.PATH
  is '���ӱ����洢·��';
comment on column ELECTRONICPOLICY.ABSOLUTEPATH
  is '���ӱ����洢����·��';
comment on column ELECTRONICPOLICY.ITEMID
  is '��Ӧ�ϴ�CM���ITEMID';
comment on column ELECTRONICPOLICY.DATIME
  is '�ϴ�ʱ�䣬ȡ����д���current timestamp';
comment on column ELECTRONICPOLICY.CMTIME
  is '�ϴ�CMʱ�䣬ȡ�ϴ�CM������current timestamp';
comment on column ELECTRONICPOLICY.CREATETIME
  is '����ʱ��';
comment on column ELECTRONICPOLICY.UPDATETIME
  is '���ݸ���ʱ��';
comment on column ELECTRONICPOLICY.ISDOWNLOAD
  is '�Ƿ����ع�';
comment on column ELECTRONICPOLICY.DOWNLOADCOUNT
  is '���ش���';
comment on column ELECTRONICPOLICY.LASTDOWNLOADTIME
  is '���һ������ʱ��';
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
  is '���';
comment on column INSURANCEPOLICY.TRANSIDENTIFY
  is '���ױ�ʾ';
comment on column INSURANCEPOLICY.TRANSSERIALNUMBER
  is '������ˮ��';
comment on column INSURANCEPOLICY.MERCHANTTRANSSERIALNUMBER
  is '�̼ҽ�����ˮ��';
comment on column INSURANCEPOLICY.ORDERSERIALNUMBER
  is '������';
comment on column INSURANCEPOLICY.MERCHANTORDERNUMBER
  is '��������������';
comment on column INSURANCEPOLICY.POLICYSERIALNUMBER
  is '������';
comment on column INSURANCEPOLICY.APPLICATIONNUMBER
  is 'Ͷ������';
comment on column INSURANCEPOLICY.APPLICATIONSERIALNUMBER
  is 'Ͷ����ӡˢ��';
comment on column INSURANCEPOLICY.ELECTRONICPOLICYNUMBER
  is '���ӱ�����';
comment on column INSURANCEPOLICY.ELECTRONICAPPLICANTNUMBER
  is '����Ͷ������';
comment on column INSURANCEPOLICY.POLICYTYPE
  is '��������';
comment on column INSURANCEPOLICY.DOCUMENTTYPE
  is '���յ�֤����';
comment on column INSURANCEPOLICY.OVERDUEPREMIUMPAYMENTOPTION
  is '���ڽɸ�����ѡ��(���ѹ���δ��ѡ��)';
comment on column INSURANCEPOLICY.POLICYSTATUS
  is '����״̬';
comment on column INSURANCEPOLICY.POLICYSTATUSNAME
  is '����״̬����';
comment on column INSURANCEPOLICY.POLICYSTATUSDESC
  is '����״̬����';
comment on column INSURANCEPOLICY.GENERATEELECTRONICPOLICYSTATUS
  is '���ɵ��ӱ���״̬';
comment on column INSURANCEPOLICY.PRODUCTCODE
  is '��Ʒ����';
comment on column INSURANCEPOLICY.PRODUCTNAME
  is '��Ʒ����';
comment on column INSURANCEPOLICY.MERCHANTPRODUCTCODE
  is '����������Ʒ����';
comment on column INSURANCEPOLICY.MERCHANTPRODUCTNAME
  is '����������Ʒ����';
comment on column INSURANCEPOLICY.COMBOCODE
  is '�ײʹ���';
comment on column INSURANCEPOLICY.COMBONAME
  is '�ײ�����';
comment on column INSURANCEPOLICY.INSUREDAMOUNT
  is '����';
comment on column INSURANCEPOLICY.PREMIUM
  is '����';
comment on column INSURANCEPOLICY.DISCOUNTPREMIUM
  is '�ۿ۱���';
comment on column INSURANCEPOLICY.GROSSPREMIUM
  is '�ܱ���';
comment on column INSURANCEPOLICY.FACEAMOUNT
  is '�ܱ���';
comment on column INSURANCEPOLICY.FIRSTPREMIUM
  is '���ڱ���';
comment on column INSURANCEPOLICY.INITIALPREMAMT
  is '�״νɷѽ��';
comment on column INSURANCEPOLICY.BENEFITPERIOD
  is '��������';
comment on column INSURANCEPOLICY.BENEFITPERIODTYPE
  is '������������';
comment on column INSURANCEPOLICY.PAYMENTMODE
  is '�ɷѷ�ʽ';
comment on column INSURANCEPOLICY.PAYMENTDURATION
  is '�ɷ�����';
comment on column INSURANCEPOLICY.PAYMENTDURATIONMODE
  is '�ɷ���������';
comment on column INSURANCEPOLICY.SIGNEDDATE
  is 'ǩ������';
comment on column INSURANCEPOLICY.INCEPTIONDATE
  is '������Ч��';
comment on column INSURANCEPOLICY.APPLICATIONDATE
  is '����������';
comment on column INSURANCEPOLICY.BACKTRACKDATE
  is '����������';
comment on column INSURANCEPOLICY.INSURANCESTARTPERIOD
  is '��������';
comment on column INSURANCEPOLICY.INSURANCEENDPERIOD
  is '����ֹ��';
comment on column INSURANCEPOLICY.SUBMISSIONDATE
  is 'Ͷ����������';
comment on column INSURANCEPOLICY.BENEFICIARYMODE
  is '���淽ʽ';
comment on column INSURANCEPOLICY.TRAVELDESTINATION
  is '����Ŀ�ĵ�';
comment on column INSURANCEPOLICY.PAYMENTMETHOD
  is '֧����ʽ';
comment on column INSURANCEPOLICY.BENEFITMODE
  is '�������ȡ��ʽ';
comment on column INSURANCEPOLICY.DIVTYPE
  is '������ȡ��ʽ';
comment on column INSURANCEPOLICY.ANNUITYPAYOUTDURATION
  is '�����ȡ����';
comment on column INSURANCEPOLICY.ANNUITYPAYOUTDURATIONMODE
  is '�����ȡ��������';
comment on column INSURANCEPOLICY.PAYOUTSTART
  is '����ȡ��ʼ����';
comment on column INSURANCEPOLICY.PAYOUTEND
  is '�����ȡ��ֹ����';
comment on column INSURANCEPOLICY.EXCESSPREMAMT
  is '���ӱ���/�������';
comment on column INSURANCEPOLICY.POLICYDELIVERYFEE
  is 'ֽ�ʱ�����ݷ�';
comment on column INSURANCEPOLICY.POLICYSTATUSMESSAGE
  is '�Զ��˱�����';
comment on column INSURANCEPOLICY.UNITCOUNT
  is '����';
comment on column INSURANCEPOLICY.FORMID
  is 'Ͷ��������';
comment on column INSURANCEPOLICY.SPECIALSTATEMENT
  is '�ر�����';
comment on column INSURANCEPOLICY.CONTRACTNAMES
  is '�Ķ�����������Ϣ';
comment on column INSURANCEPOLICY.FUNDTRANSFERDATEBASEDON
  is '���ѽ���Ͷ���˻�����ѡ��';
comment on column INSURANCEPOLICY.BANKCODE
  is '�ɷ�����/�������б���';
comment on column INSURANCEPOLICY.REQUIREVISA
  is '�Ƿ���Ҫǩ֤';
comment on column INSURANCEPOLICY.CERTIFICATIONREQUIRED
  is '�Ƿ���ҪӢ��֤��';
comment on column INSURANCEPOLICY.DELIVERYINVOICE
  is '�Ƿ��ṩ��Ʊ';
comment on column INSURANCEPOLICY.DELIVERYEPOLICY
  is '�Ƿ���Ҫ���ӱ���';
comment on column INSURANCEPOLICY.DELIVERYHARDCOPY
  is '�Ƿ�Ͷ��ֽ�ʱ���';
comment on column INSURANCEPOLICY.CAMPAIGNCODE
  is '�����';
comment on column INSURANCEPOLICY.CAMPAIGNNAME
  is '�����';
comment on column INSURANCEPOLICY.DISCOUNTTYPECODE
  is '�ۿ۴���';
comment on column INSURANCEPOLICY.DISCOUNTRATE
  is '�ۿ�ϵ��';
comment on column INSURANCEPOLICY.AUTORENEWABLE
  is '������һ�����Զ����� ';
comment on column INSURANCEPOLICY.AGENTCODE
  is '�����˴���';
comment on column INSURANCEPOLICY.AGREEMENTNO
  is '������Э���';
comment on column INSURANCEPOLICY.AGENTNAME
  is '����������';
comment on column INSURANCEPOLICY.DEPARTMENTNO
  is '���������ڻ�������';
comment on column INSURANCEPOLICY.DEPARTMENTNAME
  is '���������ڻ�������';
comment on column INSURANCEPOLICY.INSTITUTIONCODE
  is '��������';
comment on column INSURANCEPOLICY.INSTITUTIONNAME
  is '��������';
comment on column INSURANCEPOLICY.INTERMEDIARYCODE
  is '�������� ';
comment on column INSURANCEPOLICY.INTERMEDIARYNAME
  is '��������';
comment on column INSURANCEPOLICY.BRANCHCODE
  is '�������';
comment on column INSURANCEPOLICY.BRANCHNAME
  is '��������';
comment on column INSURANCEPOLICY.ORGANIZATIONCODE
  is '������������';
comment on column INSURANCEPOLICY.ORGANIZATIONNAME
  is '������������';
comment on column INSURANCEPOLICY.PERSONALUSERSERIALNO
  is '���˿ͻ�';
comment on column INSURANCEPOLICY.SYNCSTATUS
  is 'ͬ��״̬';
comment on column INSURANCEPOLICY.SYNCSTATUSDESC
  is 'ͬ��״̬����';
comment on column INSURANCEPOLICY.SYNCSTARTTIME
  is 'ͬ����ʼʱ��';
comment on column INSURANCEPOLICY.SYNCENDTIME
  is 'ͬ������ʱ��';
comment on column INSURANCEPOLICY.CASHVALUE
  is '�ֽ��ֵ';
comment on column INSURANCEPOLICY.ACTUALAVAILABLEAMOUNT
  is 'ʵ�ʿ�֧ȡ���';
comment on column INSURANCEPOLICY.REVERSALFLAG
  is '�Ƿ�ɳ���';
comment on column INSURANCEPOLICY.MUSTCANCELFLAG
  is 'ȫ���������ȡ�Ƿ�����˱� ';
comment on column INSURANCEPOLICY.ALLOWPARTYWITHDROWFLAG
  is '�Ƿ�������֧ȡ';
comment on column INSURANCEPOLICY.PRESERVEACCEPTTIME
  is '��ȫ����ʱ��';
comment on column INSURANCEPOLICY.PRESERVEEFFECTIVETIME
  is '��ȫ��Чʱ��';
comment on column INSURANCEPOLICY.REFUNDPOLICYTIME
  is '����ʱ��';
comment on column INSURANCEPOLICY.REFUNDPOLICYSUCCESSTIME
  is '�����ɹ�ʱ��';
comment on column INSURANCEPOLICY.CUSTOMERID
  is '�û���';
comment on column INSURANCEPOLICY.EXPAYMODE
  is '���ڽɷ���ʽ';
comment on column INSURANCEPOLICY.GETPOLMODE
  is '�������ͷ�ʽ';
comment on column INSURANCEPOLICY.PASSWORD
  is '��������';
comment on column INSURANCEPOLICY.SPECCONTENT
  is '�ر�Լ��';
comment on column INSURANCEPOLICY.TEMPFEENO
  is '��Ʊӡˢ����';
comment on column INSURANCEPOLICY.AGENTGROUP
  is '���������';
comment on column INSURANCEPOLICY.DISPUTEDFLAG
  is '��ͬ���鴦��ʽ';
comment on column INSURANCEPOLICY.ACNAME
  is '�ٲ�ίԱ������';
comment on column INSURANCEPOLICY.ISVISIT
  is '�Ƿ�ط�';
comment on column INSURANCEPOLICY.ISBIND
  is '�󶨱�־';
comment on column INSURANCEPOLICY.INSUREINFORMBOOKNUMBER
  is '��֪��';
comment on column INSURANCEPOLICY.OPERATORID
  is '����Ա';
comment on column INSURANCEPOLICY.CREATETIME
  is '����ʱ��';
comment on column INSURANCEPOLICY.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSERIALNUMBER
  is '�ʽ𻮲���ˮ��';
comment on column FUNDSTRANSFER.MERCHANTORDERNUMBER
  is '�������������ţ��Ա������ţ�';
comment on column FUNDSTRANSFER.FUNDSTRANSFERORDERNUMBER
  is '�ʽ𻮲�������';
comment on column FUNDSTRANSFER.POLICYSERIALNUMBER
  is '������';
comment on column FUNDSTRANSFER.ACCOUNTNUMBER
  is '�ʽ𻮲��˺�';
comment on column FUNDSTRANSFER.ACCTHOLDERID
  is '�ʺų�����Id';
comment on column FUNDSTRANSFER.ACCTHOLDERNAME
  is '�ʺų���������';
comment on column FUNDSTRANSFER.CASHVALUE
  is '�ֽ��ֵ';
comment on column FUNDSTRANSFER.ACTUALAVAILABLEAMOUNT
  is 'ʵ�ʿ�֧ȡ���';
comment on column FUNDSTRANSFER.TRANSFERTYPE
  is '��������';
comment on column FUNDSTRANSFER.TRANSFERAMOUNT
  is '�������';
comment on column FUNDSTRANSFER.TRANSFERTIME
  is '����ʱ��';
comment on column FUNDSTRANSFER.PRESERVEACCEPTTIME
  is '��ȫ����ʱ��';
comment on column FUNDSTRANSFER.PRESERVEEFFECTIVETIME
  is '��ȫ��Чʱ��';
comment on column FUNDSTRANSFER.TRANSFERSUCCESSTIME
  is '�����ɹ�ʱ��';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSTATUS
  is '�ʽ𻮲�״̬';
comment on column FUNDSTRANSFER.FUNDSTRANSFERSTATUSDESC
  is '�ʽ𻮲�״̬����';
comment on column FUNDSTRANSFER.CHECKPAYNUMBER
  is '���˵���';
comment on column FUNDSTRANSFER.ACCOUNTDATE
  is '��������';
comment on column FUNDSTRANSFER.REFUNDPOLICYTIME
  is '����ʱ��';
comment on column FUNDSTRANSFER.REFUNDPOLICYSUCCESSTIME
  is '�����ɹ�ʱ��';
comment on column FUNDSTRANSFER.SYNCSTATUS
  is 'ͬ��״̬';
comment on column FUNDSTRANSFER.SYNCSTATUSDESC
  is 'ͬ��״̬����';
comment on column FUNDSTRANSFER.SYNCSTARTTIME
  is 'ͬ����ʼʱ��';
comment on column FUNDSTRANSFER.SYNCENDTIME
  is 'ͬ������ʱ��';
comment on column FUNDSTRANSFER.OPERATORID
  is '����Ա';
comment on column FUNDSTRANSFER.POLICYSERIALNO
  is '����';
comment on column FUNDSTRANSFER.CREATETIME
  is '����ʱ��';
comment on column FUNDSTRANSFER.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column INSURANCEPOLICYCACHE.MERCHANTORDERNUMBER
  is '�̼Ҷ�����';
comment on column INSURANCEPOLICYCACHE.POLICYSERIALNUMBER
  is '������';
comment on column INSURANCEPOLICYCACHE.INSTITUTIONCODE
  is '������������';
comment on column INSURANCEPOLICYCACHE.INSTITUTIONNAME
  is '������������';
comment on column INSURANCEPOLICYCACHE.REQUESTTIME
  is '��һ������ʱ��';
comment on column INSURANCEPOLICYCACHE.LASTREQUESTTIME
  is '���һ������ʱ��';
comment on column INSURANCEPOLICYCACHE.CREATETIME
  is '����ʱ��';
comment on column INSURANCEPOLICYCACHE.UPDATETIME
  is '����ʱ��';

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
  is '���';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYID
  is '����/����ID';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYCODE
  is '����/���ִ���';
comment on column INSURANCEPOLICYLIABILITY.CORECODE
  is '���ı���/���ִ���';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYNAME
  is '����/��������';
comment on column INSURANCEPOLICYLIABILITY.PRODUCTCODE
  is '��Ʒ����';
comment on column INSURANCEPOLICYLIABILITY.PRODUCTNAME
  is '��Ʒ����';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTPRODUCTCODE
  is '�̼Ҳ�Ʒ����';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTPRODUCTNAME
  is '�̼Ҳ�Ʒ����';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTLIABILITYCODE
  is '�̼����ִ���';
comment on column INSURANCEPOLICYLIABILITY.MERCHANTLIABILITYNAME
  is '�̼����ִ���';
comment on column INSURANCEPOLICYLIABILITY.SUBRISKFLAG
  is '�����ձ�־';
comment on column INSURANCEPOLICYLIABILITY.LIABILITYORDER
  is '����/����˳��';
comment on column INSURANCEPOLICYLIABILITY.GROUPTYPE
  is 'Ⱥ������';
comment on column INSURANCEPOLICYLIABILITY.INSUREDAMOUNT
  is '����';
comment on column INSURANCEPOLICYLIABILITY.PREMIUM
  is '����';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTPREMIUM
  is '�ۿ۱��� ';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTTYPECODE
  is '�ۿ۴���';
comment on column INSURANCEPOLICYLIABILITY.DISCOUNTRATE
  is '�ۿ�ϵ�� ';
comment on column INSURANCEPOLICYLIABILITY.BENEFITPERIOD
  is '��������';
comment on column INSURANCEPOLICYLIABILITY.BENEFITPERIODTYPE
  is '������������';
comment on column INSURANCEPOLICYLIABILITY.INSURANCESTARTPERIOD
  is '��������';
comment on column INSURANCEPOLICYLIABILITY.INSURANCEENDPERIOD
  is '����ֹ��';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTMODE
  is '�ɷѷ�ʽ';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTDURATION
  is '�ɷ�����';
comment on column INSURANCEPOLICYLIABILITY.PAYMENTDURATIONMODE
  is '�ɷ���������';
comment on column INSURANCEPOLICYLIABILITY.DEDUCTIBLES
  is '�����';
comment on column INSURANCEPOLICYLIABILITY.LOSSRATIO
  is '�⸶����';
comment on column INSURANCEPOLICYLIABILITY.SEQINDEX
  is '��ʾ˳��';
comment on column INSURANCEPOLICYLIABILITY.POLICYSERIALNO
  is '����';
comment on column INSURANCEPOLICYLIABILITY.PARENTSERIALNO
  is '������/���ִ���';
comment on column INSURANCEPOLICYLIABILITY.MAINRISKCODE
  is '�������ִ���';
comment on column INSURANCEPOLICYLIABILITY.RISKTYPE
  is '��������';
comment on column INSURANCEPOLICYLIABILITY.RATE
  is '����';
comment on column INSURANCEPOLICYLIABILITY.INCEPTIONDATE
  is '��Ч��';
comment on column INSURANCEPOLICYLIABILITY.RANK
  is '����';
comment on column INSURANCEPOLICYLIABILITY.UNITCOUNT
  is '����';
comment on column INSURANCEPOLICYLIABILITY.COSTINTV
  is '�ۿ���';
comment on column INSURANCEPOLICYLIABILITY.COSTDATE
  is '�ۿ�ʱ��';
comment on column INSURANCEPOLICYLIABILITY.SPECCONTENT
  is '�ر�Լ��';
comment on column INSURANCEPOLICYLIABILITY.PAYENDYEARFLAG
  is '�ɷ����������־';
comment on column INSURANCEPOLICYLIABILITY.PAYENDYEAR
  is '�ɷ���������';
comment on column INSURANCEPOLICYLIABILITY.GETYEARFLAG
  is '��ȡ�������ڱ�־';
comment on column INSURANCEPOLICYLIABILITY.GETYEAR
  is '��ȡ����';
comment on column INSURANCEPOLICYLIABILITY.INSUYEARFLAG
  is '�������������־';
comment on column INSURANCEPOLICYLIABILITY.INSUYEAR
  is '������������';
comment on column INSURANCEPOLICYLIABILITY.GETINTV
  is '��ȡ��ʽ';
comment on column INSURANCEPOLICYLIABILITY.GETBANKCODE
  is '��ȡ���б���';
comment on column INSURANCEPOLICYLIABILITY.GETBANKACCNO
  is '��ȡ�����˻�';
comment on column INSURANCEPOLICYLIABILITY.GETACCNAME
  is '��ȡ���л���';
comment on column INSURANCEPOLICYLIABILITY.GETACCPROVINCE
  is '��ȡ����ʡ����';
comment on column INSURANCEPOLICYLIABILITY.GETACCCITY
  is '��ȡ�����б���';
comment on column INSURANCEPOLICYLIABILITY.GETACCTYPE
  is '��ȡ���п�������';
comment on column INSURANCEPOLICYLIABILITY.AUTOPAYFLAG
  is '�潻��־';
comment on column INSURANCEPOLICYLIABILITY.BONUSPAYMODE
  is '����������';
comment on column INSURANCEPOLICYLIABILITY.SUBFLAG
  is '������־';
comment on column INSURANCEPOLICYLIABILITY.BONUSGETMODE
  is '������ȡ��ʽ';
comment on column INSURANCEPOLICYLIABILITY.AUTORNEWFLAG
  is '�Զ�������־';
comment on column INSURANCEPOLICYLIABILITY.HEALTHFLAG
  is '������֮��־';
comment on column INSURANCEPOLICYLIABILITY.FULLBONUSGETMODE
  is '������ȡ����ȡ��ʽ';
comment on column INSURANCEPOLICYLIABILITY.FIRSTRATE
  is '��ʼ������';
comment on column INSURANCEPOLICYLIABILITY.SURERATE
  is '��֤����';
comment on column INSURANCEPOLICYLIABILITY.OPERATORID
  is '����Ա';
comment on column INSURANCEPOLICYLIABILITY.CREATETIME
  is '����ʱ��';
comment on column INSURANCEPOLICYLIABILITY.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column INSURANCEPOLICYPROCESS.MERCHANTORDERNUMBER
  is '�Ա�������';
comment on column INSURANCEPOLICYPROCESS.POLICYSERIALNUMBER
  is '������';
comment on column INSURANCEPOLICYPROCESS.INSTITUTIONCODE
  is '������������';
comment on column INSURANCEPOLICYPROCESS.INSTITUTIONNAME
  is '������������';
comment on column INSURANCEPOLICYPROCESS.SERVICENAME
  is '��������';
comment on column INSURANCEPOLICYPROCESS.LOCKSTATUS
  is '����״̬';
comment on column INSURANCEPOLICYPROCESS.LOCKEDREASON
  is '����ԭ��';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTATUS
  is '����״̬';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTARTTIME
  is '����ʼʱ��';
comment on column INSURANCEPOLICYPROCESS.PROCESSENDTIME
  is '�������ʱ��';
comment on column INSURANCEPOLICYPROCESS.PROCESSSTATUSDESC
  is '����״̬����';
comment on column INSURANCEPOLICYPROCESS.CREATETIME
  is '����ʱ��';
comment on column INSURANCEPOLICYPROCESS.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column PARTYROLEINPOLICY.ROLESERIALNO
  is '��ɫ���';
comment on column PARTYROLEINPOLICY.FIRSTNAME
  is '��';
comment on column PARTYROLEINPOLICY.LASTNAME
  is '��';
comment on column PARTYROLEINPOLICY.FULLNAME
  is '����';
comment on column PARTYROLEINPOLICY.FULLENNAME
  is 'Ӣ������';
comment on column PARTYROLEINPOLICY.IDTYPE
  is '֤������';
comment on column PARTYROLEINPOLICY.IDNUMBER
  is '֤������';
comment on column PARTYROLEINPOLICY.IDEXPDATE
  is '֤����Ч����';
comment on column PARTYROLEINPOLICY.GENDER
  is '�Ա�';
comment on column PARTYROLEINPOLICY.BIRTHDATE
  is '��������';
comment on column PARTYROLEINPOLICY.MARITALSTATUS
  is '����״��';
comment on column PARTYROLEINPOLICY.AGEINDAY
  is '��������';
comment on column PARTYROLEINPOLICY.AGE
  is '����';
comment on column PARTYROLEINPOLICY.BIRTHWEIGHT
  is '��������';
comment on column PARTYROLEINPOLICY.HEIGHT
  is '���';
comment on column PARTYROLEINPOLICY.WEIGHT
  is '����';
comment on column PARTYROLEINPOLICY.MOBILEPHONENUMBER
  is '�ֻ��绰';
comment on column PARTYROLEINPOLICY.PHONENUMBER
  is '�̶��绰';
comment on column PARTYROLEINPOLICY.OFFICEPHONENUMBER
  is '�칫�绰';
comment on column PARTYROLEINPOLICY.EMAIL
  is '��������';
comment on column PARTYROLEINPOLICY.POSTALCODE
  is '��������';
comment on column PARTYROLEINPOLICY.PROVINCE
  is 'ʡ��';
comment on column PARTYROLEINPOLICY.CITY
  is '����';
comment on column PARTYROLEINPOLICY.ADDRESSLINES
  is 'ͨѶ��ַ';
comment on column PARTYROLEINPOLICY.OFFICEADDRESS
  is '�칫��ַ';
comment on column PARTYROLEINPOLICY.HOMEADDRESS
  is '��ͥ��ַ';
comment on column PARTYROLEINPOLICY.ROLEKIND
  is '��ɫ����';
comment on column PARTYROLEINPOLICY.ROLEORDER
  is '��ɫ˳��';
comment on column PARTYROLEINPOLICY.RELATEDTOAPPLICANT
  is '��Ͷ���˹�ϵ';
comment on column PARTYROLEINPOLICY.RELATEDTOINSURED
  is '�뱻�����˹�ϵ';
comment on column PARTYROLEINPOLICY.MAININSUREDFLAG
  is '�������˱�־';
comment on column PARTYROLEINPOLICY.INSUREDAMOUNTPERCENT
  is '��ռ����ٷֱ�';
comment on column PARTYROLEINPOLICY.BENEFICIARYTYPE
  is '����������';
comment on column PARTYROLEINPOLICY.INTERESTPERCENT
  is '����ݶ�';
comment on column PARTYROLEINPOLICY.CITIZENSHIP
  is '����';
comment on column PARTYROLEINPOLICY.OCCUPATIONCLASS
  is 'ְҵ���';
comment on column PARTYROLEINPOLICY.OCCUPATIONCODE
  is 'ְҵ����';
comment on column PARTYROLEINPOLICY.OCCUPATIONDESCRIPTION
  is '��������';
comment on column PARTYROLEINPOLICY.ANNUALINCOME
  is '������';
comment on column PARTYROLEINPOLICY.EMPLOYERFULLNAME
  is '������λ����';
comment on column PARTYROLEINPOLICY.DRINKERAMOUNTBYYEAR
  is '��������/��';
comment on column PARTYROLEINPOLICY.DRINKERSTATUS
  is '����״��';
comment on column PARTYROLEINPOLICY.DRINKERYEARS
  is '��������';
comment on column PARTYROLEINPOLICY.SMOKERAMOUNTBYYEAR
  is '��������/��';
comment on column PARTYROLEINPOLICY.SMOKERSTATUS
  is '����״��';
comment on column PARTYROLEINPOLICY.SMOKERYEARS
  is '��������';
comment on column PARTYROLEINPOLICY.ACCEPTSMS
  is '�Ƿ�����ֻ����ŷ���';
comment on column PARTYROLEINPOLICY.INDICATORMESSAGE
  is '��֪��Ϣ';
comment on column PARTYROLEINPOLICY.SAMEINDUSTRYINSUREDAMOUNT
  is 'ͬҵ����';
comment on column PARTYROLEINPOLICY.CUSTOMERID
  is '�ͻ���';
comment on column PARTYROLEINPOLICY.CUSTOMFLAG
  is '�ɿͻ���־';
comment on column PARTYROLEINPOLICY.PARENTSERIALNO
  is '����ɫ';
comment on column PARTYROLEINPOLICY.POLICYSERIALNO
  is '������';
comment on column PARTYROLEINPOLICY.IDVALIDFLAG
  is '֤���Ƿ�������Ч:1-��;0-��;����';
comment on column PARTYROLEINPOLICY.IDSTARTDATE
  is '֤������';
comment on column PARTYROLEINPOLICY.IDENDDATE
  is '֤��ֹ��';
comment on column PARTYROLEINPOLICY.HOUSEHOLD
  is '����';
comment on column PARTYROLEINPOLICY.COUNTY
  is '��';
comment on column PARTYROLEINPOLICY.HOMEZIPCODE
  is '�ʼ��ʱ�';
comment on column PARTYROLEINPOLICY.GRPNAME
  is '��˾����';
comment on column PARTYROLEINPOLICY.CITIZENSHIPNAME
  is '��������';
comment on column PARTYROLEINPOLICY.FAX
  is '����';
comment on column PARTYROLEINPOLICY.LICENSETYPE
  is '��������';
comment on column PARTYROLEINPOLICY.LICENSETYPEVIEW
  is '�������� ��ʾ��';
comment on column PARTYROLEINPOLICY.ALIAS
  is '�ǳ�';
comment on column PARTYROLEINPOLICY.HOBBY
  is '����';
comment on column PARTYROLEINPOLICY.BLOODTYPE
  is 'Ѫ��';
comment on column PARTYROLEINPOLICY.CREATETIME
  is '����ʱ��';
comment on column PARTYROLEINPOLICY.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column INSUREDLIABILITY.LIABILITYID
  is '����/����ID';
comment on column INSUREDLIABILITY.LIABILITYCODE
  is '����/���ִ���';
comment on column INSUREDLIABILITY.CORECODE
  is '���ı���/���ִ���';
comment on column INSUREDLIABILITY.LIABILITYNAME
  is '����/��������';
comment on column INSUREDLIABILITY.PRODUCTCODE
  is '��Ʒ����';
comment on column INSUREDLIABILITY.PRODUCTNAME
  is '��Ʒ����';
comment on column INSUREDLIABILITY.SUBRISKFLAG
  is '�����ձ�־';
comment on column INSUREDLIABILITY.GROUPTYPE
  is 'Ⱥ������';
comment on column INSUREDLIABILITY.INSUREDAMOUNT
  is '����';
comment on column INSUREDLIABILITY.PREMIUM
  is '����';
comment on column INSUREDLIABILITY.DISCOUNTPREMIUM
  is '�ۿ۱���';
comment on column INSUREDLIABILITY.DISCOUNTTYPECODE
  is '�ۿ۴���';
comment on column INSUREDLIABILITY.DISCOUNTRATE
  is '�ۿ�ϵ��';
comment on column INSUREDLIABILITY.BENEFITPERIOD
  is '��������';
comment on column INSUREDLIABILITY.BENEFITPERIODTYPE
  is '������������';
comment on column INSUREDLIABILITY.INSURANCESTARTPERIOD
  is '��������';
comment on column INSUREDLIABILITY.INSURANCEENDPERIOD
  is '����ֹ��';
comment on column INSUREDLIABILITY.PAYMENTMODE
  is '�ɷѷ�ʽ';
comment on column INSUREDLIABILITY.PAYMENTDURATION
  is '�ɷ�����';
comment on column INSUREDLIABILITY.PAYMENTDURATIONMODE
  is '�ɷ���������';
comment on column INSUREDLIABILITY.DEDUCTIBLES
  is '�����';
comment on column INSUREDLIABILITY.LOSSRATIO
  is '�⸶����';
comment on column INSUREDLIABILITY.SEQINDEX
  is '��ʾ˳��';
comment on column INSUREDLIABILITY.INSUREDSERIALNO
  is '������';
comment on column INSUREDLIABILITY.PARENTSERIALNO
  is '������/���ִ���';
comment on column INSUREDLIABILITY.OPERATORID
  is '����Ա';
comment on column INSUREDLIABILITY.CREATETIME
  is '����ʱ��';
comment on column INSUREDLIABILITY.UPDATETIME
  is '����ʱ��';
comment on column INSUREDLIABILITY.LIABILITYORDER
  is '����/����˳�� ';
comment on column INSUREDLIABILITY.MAINRISKCODE
  is '�������ִ���';
comment on column INSUREDLIABILITY.RISKTYPE
  is '��������';
comment on column INSUREDLIABILITY.RATE
  is '����';
comment on column INSUREDLIABILITY.INCEPTIONDATE
  is '��Ч��';
comment on column INSUREDLIABILITY.RANK
  is '����';
comment on column INSUREDLIABILITY.UNITCOUNT
  is '����';
comment on column INSUREDLIABILITY.COSTINTV
  is '�ۿ���';
comment on column INSUREDLIABILITY.COSTDATE
  is '�ۿ�ʱ��';
comment on column INSUREDLIABILITY.SPECCONTENT
  is '�ر�Լ��';
comment on column INSUREDLIABILITY.PAYENDYEARFLAG
  is '�ɷ����������־';
comment on column INSUREDLIABILITY.PAYENDYEAR
  is '�ɷ���������';
comment on column INSUREDLIABILITY.GETYEARFLAG
  is '��ȡ�������ڱ�־';
comment on column INSUREDLIABILITY.GETYEAR
  is '��ȡ����';
comment on column INSUREDLIABILITY.INSUYEARFLAG
  is '�������������־';
comment on column INSUREDLIABILITY.INSUYEAR
  is '������������';
comment on column INSUREDLIABILITY.GETINTV
  is '��ȡ��ʽ';
comment on column INSUREDLIABILITY.GETBANKCODE
  is '��ȡ���б���';
comment on column INSUREDLIABILITY.GETBANKACCNO
  is '��ȡ�����˻�';
comment on column INSUREDLIABILITY.GETACCNAME
  is '��ȡ���л���';
comment on column INSUREDLIABILITY.GETACCPROVINCE
  is '��ȡ����ʡ����';
comment on column INSUREDLIABILITY.GETACCCITY
  is '��ȡ�����б���';
comment on column INSUREDLIABILITY.GETACCTYPE
  is '��ȡ���п�������';
comment on column INSUREDLIABILITY.AUTOPAYFLAG
  is '�潻��־';
comment on column INSUREDLIABILITY.BONUSPAYMODE
  is '����������';
comment on column INSUREDLIABILITY.SUBFLAG
  is '������־';
comment on column INSUREDLIABILITY.BONUSGETMODE
  is '������ȡ��ʽ';
comment on column INSUREDLIABILITY.AUTORNEWFLAG
  is '�Զ�������־';
comment on column INSUREDLIABILITY.HEALTHFLAG
  is '������֮��־';
comment on column INSUREDLIABILITY.FULLBONUSGETMODE
  is '������ȡ����ȡ��ʽ';
comment on column INSUREDLIABILITY.FIRSTRATE
  is '��ʼ������';
comment on column INSUREDLIABILITY.SURERATE
  is '��֤����';
comment on column INSUREDLIABILITY.MERCHANTPRODUCTCODE
  is '�̼Ҳ�Ʒ���� ';
comment on column INSUREDLIABILITY.MERCHANTPRODUCTNAME
  is '�̼Ҳ�Ʒ����';
comment on column INSUREDLIABILITY.MERCHANTLIABILITYCODE
  is '�̼����ִ���';
comment on column INSUREDLIABILITY.MERCHANTLIABILITYNAME
  is '�̼����ִ���';
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
  is '���';
comment on column INSUREINFORMBOOK.INFORMCODE
  is '��֪����';
comment on column INSUREINFORMBOOK.INFORMTYPE
  is '��֪����';
comment on column INSUREINFORMBOOK.INFORMTYPENAME
  is '��֪��������';
comment on column INSUREINFORMBOOK.INFORMORDER
  is '��֪˳��';
comment on column INSUREINFORMBOOK.INFORMCONTENT
  is '��֪����';
comment on column INSUREINFORMBOOK.POLICYSERIALNO
  is '����';
comment on column INSUREINFORMBOOK.TELLVERSION
  is '��֪���';
comment on column INSUREINFORMBOOK.TELLREMARK
  is '��֪��ע';
comment on column INSUREINFORMBOOK.CREATETIME
  is '����ʱ��';
comment on column INSUREINFORMBOOK.UPDATETIME
  is '����ʱ�� ';
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
  is '���';
comment on column INVOICE.INVOICETYPE
  is '��Ʊ����';
comment on column INVOICE.INVOICETITLE
  is '��Ʊ̧ͷ';
comment on column INVOICE.INVOINCECONTENT
  is '��Ʊ����';
comment on column INVOICE.COMPANYNAME
  is '��λ����';
comment on column INVOICE.REGISTEREDADDRESS
  is 'ע���ַ';
comment on column INVOICE.REGISTEREDPHONE
  is 'ע��绰';
comment on column INVOICE.OPENINGBANK
  is '��������';
comment on column INVOICE.TAXREGISTRYNO
  is '˰��';
comment on column INVOICE.INVOICECODE
  is '��Ʊ����';
comment on column INVOICE.INVOICENO
  is '��Ʊ��';
comment on column INVOICE.PRINTNO
  is '��ӡ������';
comment on column INVOICE.PRINTERMACHINENO
  is '��ӡ�����';
comment on column INVOICE.TAXCONTROLCODE
  is '˰����';
comment on column INVOICE.CURRENCYSYMBOL
  is '���ҷ���';
comment on column INVOICE.TOTALINFIGURES
  is 'Сд�ϼ�';
comment on column INVOICE.TOTALINLETTERS
  is '��д�ϼ�';
comment on column INVOICE.PAYEE
  is '�տλ';
comment on column INVOICE.PAYER
  is '���λ(����)';
comment on column INVOICE.BANKACCOUNT
  is '�����ʻ�';
comment on column INVOICE.CASHIER
  is '�տ�Ա';
comment on column INVOICE.PAYEESEAL
  is '�տλӡ��';
comment on column INVOICE.POLICYSERIALNO
  is '����';
comment on column INVOICE.CREATETIME
  is '����ʱ��';
comment on column INVOICE.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column INVOICEITEM.ITEM
  is '��Ŀ��';
comment on column INVOICEITEM.UNITPRICE
  is '����';
comment on column INVOICEITEM.AMOUNT
  is '����';
comment on column INVOICEITEM.AMOUNTCHANGED
  is '���';
comment on column INVOICEITEM.INVOICESERIALNO
  is '��Ʊ';
comment on column INVOICEITEM.CREATETIME
  is '����ʱ��';
comment on column INVOICEITEM.UPDATETIME
  is '����ʱ�� ';
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
  is '���';
comment on column ORDERFORM.ORDERSERIALNUMBER
  is '������';
comment on column ORDERFORM.MERCHANTORDERNUMBER
  is '�������������ţ��Ա������ţ�';
comment on column ORDERFORM.TRANSSERIALNUMBER
  is '������ˮ��';
comment on column ORDERFORM.PRODUCTCODE
  is '��Ʒ����';
comment on column ORDERFORM.PRODUCTNAME
  is '��Ʒ����';
comment on column ORDERFORM.PRODUCTNUMBER
  is '��Ʒ����';
comment on column ORDERFORM.PRODUCTDESC
  is '��Ʒ����';
comment on column ORDERFORM.PAYERCONTACTTYPE
  is '֧������ϵ��ʽ���� (1�����ʼ� 2�ֻ�)';
comment on column ORDERFORM.PAYERCONTACT
  is '֧������ϵ��ʽ';
comment on column ORDERFORM.PAYERIP
  is '֧����IP';
comment on column ORDERFORM.PERSONALUSERSERIALNO
  is '���˿ͻ�';
comment on column ORDERFORM.CUSTOMERID
  is '�ͻ���ID';
comment on column ORDERFORM.ORDERTYPE
  is '��������';
comment on column ORDERFORM.ORDERSTATUS
  is '����״̬';
comment on column ORDERFORM.ORDERSTATUSNAME
  is '����״̬����';
comment on column ORDERFORM.ORDERSTATUSDESC
  is '����״̬����';
comment on column ORDERFORM.ORDERAMOUNT
  is '�������';
comment on column ORDERFORM.PAYMETHOD
  is '֧����ʽ';
comment on column ORDERFORM.PAYTIME
  is '֧��ʱ��';
comment on column ORDERFORM.PAYMENTMETHOD
  is '֧����ʽ';
comment on column ORDERFORM.PAYSTATUS
  is '֧��״̬';
comment on column ORDERFORM.PAYSTATUSNAME
  is '֧��״̬����';
comment on column ORDERFORM.PAYSTATUSDESC
  is '֧��״̬����';
comment on column ORDERFORM.SUBMITTIME
  is '�ύʱ��';
comment on column ORDERFORM.GATEWAYORDERNO
  is '���ض�����';
comment on column ORDERFORM.BANKORDERNO
  is '���ж�����';
comment on column ORDERFORM.REFUNDMENTFLAG
  is '�˵���ʾ';
comment on column ORDERFORM.POLICYSERIALNO
  is ' ����';
comment on column ORDERFORM.CREATETIME
  is '����ʱ��';
comment on column ORDERFORM.UPDATETIME
  is '����ʱ��';
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
  is '���';
comment on column PARTNERINSTITUTION.INSTITUTIONCODE
  is '������������';
comment on column PARTNERINSTITUTION.INSTITUTIONNAME
  is '������������';
comment on column PARTNERINSTITUTION.INSTITUTIONENNAME
  is '��������Ӣ������';
comment on column PARTNERINSTITUTION.INSTITUTIONPINYINNAME
  is '��������ƴ������';
comment on column PARTNERINSTITUTION.INSTITUTIONPROPERTY
  is '������������';
comment on column PARTNERINSTITUTION.INSTITUTIONWEBSITE
  is '����������վ';
comment on column PARTNERINSTITUTION.INSTITUTIONPROFILE
  is '�����������';
comment on column PARTNERINSTITUTION.INSTITUTIONDESC
  is '������������';
comment on column PARTNERINSTITUTION.SIGNEDDATE
  is 'ǩԼ����';
comment on column PARTNERINSTITUTION.TERMINATIONDATE
  is '��Լ����';
comment on column PARTNERINSTITUTION.OPERATORID
  is '����ԱID';
comment on column PARTNERINSTITUTION.CREATETIME
  is '����ʱ��';
comment on column PARTNERINSTITUTION.UPDATETIME
  is '����ʱ��';
comment on column PARTNERINSTITUTION.REMARK
  is '��ע';

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
  is '���';
comment on column PAYMENTACCOUNT.PAYORDERSERIALNUMBER
  is '���Ѷ�����';
comment on column PAYMENTACCOUNT.ACCOUNTNUMBER
  is '�ɷ��˺�';
comment on column PAYMENTACCOUNT.ACCOUNTNUMBERCONFIRM
  is '�ɷ�ȷ���˺�';
comment on column PAYMENTACCOUNT.ACCTHOLDERID
  is '�ʺų�����Id';
comment on column PAYMENTACCOUNT.ACCTHOLDERNAME
  is '�ʺų���������';
comment on column PAYMENTACCOUNT.ACCTIDNUMBER
  is '�ʺų��������֤����';
comment on column PAYMENTACCOUNT.ACCTMOBILEPHONE
  is '�ʺų������ֻ���';
comment on column PAYMENTACCOUNT.CARDORBOOK
  is '��/����';
comment on column PAYMENTACCOUNT.CURRENCYTYPECODE
  is '�����ʺű���';
comment on column PAYMENTACCOUNT.BANKCARDCVTWO
  is '���ÿ�У����';
comment on column PAYMENTACCOUNT.CARDEFFECTIVEDATE
  is '����Ч��';
comment on column PAYMENTACCOUNT.BANKACCTTYPE
  is '�����ʺ�����';
comment on column PAYMENTACCOUNT.BANKCODE
  is '���д���';
comment on column PAYMENTACCOUNT.BANKNAME
  is '��������';
comment on column PAYMENTACCOUNT.BANKBRANCHCODE
  is '����/֧�д���';
comment on column PAYMENTACCOUNT.BANKBRANCHNAME
  is '����/֧������';
comment on column PAYMENTACCOUNT.PAYAMOUNT
  is '֧�����';
comment on column PAYMENTACCOUNT.PAYTIME
  is '֧��ʱ��';
comment on column PAYMENTACCOUNT.CHECKPAYNUMBER
  is '���˵���';
comment on column PAYMENTACCOUNT.ACCOUNTTIME
  is '����ʱ��';
comment on column PAYMENTACCOUNT.ACCPROVINCE
  is '����ʡ����';
comment on column PAYMENTACCOUNT.ACCCITY
  is '�����б���';
comment on column PAYMENTACCOUNT.SECBANKCODE
  is '���������˻�';
comment on column PAYMENTACCOUNT.SECBANKACCNO
  is '�������б���';
comment on column PAYMENTACCOUNT.SECACCNAME
  is '�����˻�����';
comment on column PAYMENTACCOUNT.SECACCPROVINCE
  is '��������ʡ����';
comment on column PAYMENTACCOUNT.SECACCCITY
  is '���������б���';
comment on column PAYMENTACCOUNT.SECACCTYPE
  is '�������п�������';
comment on column PAYMENTACCOUNT.OPERATORID
  is '����Ա';
comment on column PAYMENTACCOUNT.POLICYSERIALNO
  is '����';
comment on column PAYMENTACCOUNT.CREATETIME
  is '����ʱ��';
comment on column PAYMENTACCOUNT.UPDATETIME
  is '����ʱ��';
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
  is '�����󶨱�';
comment on column BINDPOLICY.SERIALNO
  is '����';
comment on column BINDPOLICY.CUSTOMERID
  is '�ͻ���';
comment on column BINDPOLICY.POLICYSERIALNUMBER
  is '������';
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
  is '���';
comment on column TOPINSURED.ROLESERIALNO
  is '��ɫ���';
comment on column TOPINSURED.FIRSTNAME
  is '��';
comment on column TOPINSURED.LASTNAME
  is '��';
comment on column TOPINSURED.FULLNAME
  is '����';
comment on column TOPINSURED.FULLENNAME
  is 'Ӣ������';
comment on column TOPINSURED.IDTYPE
  is '֤������';
comment on column TOPINSURED.IDNUMBER
  is '֤������';
comment on column TOPINSURED.IDEXPDATE
  is '֤����Ч����';
comment on column TOPINSURED.GENDER
  is '�Ա�';
comment on column TOPINSURED.BIRTHDATE
  is '��������';
comment on column TOPINSURED.MARITALSTATUS
  is '����״��';
comment on column TOPINSURED.AGEINDAY
  is '��������';
comment on column TOPINSURED.AGE
  is '����';
comment on column TOPINSURED.BIRTHWEIGHT
  is '��������';
comment on column TOPINSURED.HEIGHT
  is '���';
comment on column TOPINSURED.WEIGHT
  is '����';
comment on column TOPINSURED.MOBILEPHONENUMBER
  is '�ֻ��绰';
comment on column TOPINSURED.PHONENUMBER
  is '�̶��绰';
comment on column TOPINSURED.OFFICEPHONENUMBER
  is '�칫�绰';
comment on column TOPINSURED.EMAIL
  is '��������';
comment on column TOPINSURED.POSTALCODE
  is '��������';
comment on column TOPINSURED.PROVINCE
  is 'ʡ��';
comment on column TOPINSURED.CITY
  is '����';
comment on column TOPINSURED.ADDRESSLINES
  is 'ͨѶ��ַ';
comment on column TOPINSURED.OFFICEADDRESS
  is '�칫��ַ';
comment on column TOPINSURED.HOMEADDRESS
  is '��ͥ��ַ';
comment on column TOPINSURED.ROLEKIND
  is '��ɫ����';
comment on column TOPINSURED.ROLEORDER
  is '��ɫ˳��';
comment on column TOPINSURED.RELATEDTOAPPLICANT
  is '��Ͷ���˹�ϵ';
comment on column TOPINSURED.INSUREDAMOUNTPERCENT
  is '��ռ����ٷֱ�';
comment on column TOPINSURED.CITIZENSHIP
  is '����';
comment on column TOPINSURED.OCCUPATIONCLASS
  is 'ְҵ���';
comment on column TOPINSURED.OCCUPATIONCODE
  is 'ְҵ����';
comment on column TOPINSURED.OCCUPATIONDESCRIPTION
  is '��������';
comment on column TOPINSURED.ANNUALINCOME
  is '������';
comment on column TOPINSURED.EMPLOYERFULLNAME
  is '������λ����';
comment on column TOPINSURED.DRINKERAMOUNTBYYEAR
  is '��������/��';
comment on column TOPINSURED.DRINKERSTATUS
  is '����״��';
comment on column TOPINSURED.DRINKERYEARS
  is '��������';
comment on column TOPINSURED.SMOKERAMOUNTBYYEAR
  is '��������/��';
comment on column TOPINSURED.SMOKERSTATUS
  is '����״��';
comment on column TOPINSURED.SMOKERYEARS
  is '��������';
comment on column TOPINSURED.ACCEPTSMS
  is '�Ƿ�����ֻ����ŷ���';
comment on column TOPINSURED.INDICATORMESSAGE
  is '��֪��Ϣ';
comment on column TOPINSURED.SAMEINDUSTRYINSUREDAMOUNT
  is 'ͬҵ����';
comment on column TOPINSURED.CUSTOMERID
  is '�ͻ���';
comment on column TOPINSURED.CUSTOMFLAG
  is '�ɿͻ���־';
comment on column TOPINSURED.PARENTSERIALNO
  is '����ɫ';
comment on column TOPINSURED.POLICYSERIALNO
  is '������';
comment on column TOPINSURED.IDVALIDFLAG
  is '֤���Ƿ�������Ч:1-��;0-��;����';
comment on column TOPINSURED.IDSTARTDATE
  is '֤������';
comment on column TOPINSURED.IDENDDATE
  is '֤��ֹ��';
comment on column TOPINSURED.HOUSEHOLD
  is '����';
comment on column TOPINSURED.COUNTY
  is '��';
comment on column TOPINSURED.HOMEZIPCODE
  is '�ʼ��ʱ�';
comment on column TOPINSURED.GRPNAME
  is '��˾����';
comment on column TOPINSURED.CITIZENSHIPNAME
  is '��������';
comment on column TOPINSURED.FAX
  is '����';
comment on column TOPINSURED.LICENSETYPE
  is '��������';
comment on column TOPINSURED.LICENSETYPEVIEW
  is '�������� ��ʾ��';
comment on column TOPINSURED.ALIAS
  is '�ǳ�';
comment on column TOPINSURED.HOBBY
  is '����';
comment on column TOPINSURED.BLOODTYPE
  is 'Ѫ��';
comment on column TOPINSURED.CREATETIME
  is '����ʱ��';
comment on column TOPINSURED.UPDATETIME
  is '����ʱ��';
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
  is '����ҽԺ����';
comment on column HOSPITALMANAGE.SERIALNO
  is '����';
comment on column HOSPITALMANAGE.PROVINCE
  is '��֧';
comment on column HOSPITALMANAGE.CITY
  is '����';
comment on column HOSPITALMANAGE.HOSNAME
  is 'ҽԺ����';
comment on column HOSPITALMANAGE.HOSADDR
  is 'ҽԺ��ַ';
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
  is '����';
comment on column GE_UNIVERSAL_RATE.RISKNAME
  is '��������';
comment on column GE_UNIVERSAL_RATE.CULDATE
  is '��������';
comment on column GE_UNIVERSAL_RATE.DATERATE
  is '����������';
comment on column GE_UNIVERSAL_RATE.YEARRATE
  is '�ۺ����������';
alter table GE_UNIVERSAL_RATE
  add constraint PK_GE_UNIVERSAL_RATE primary key (SERIALNO)
  using index 
  tablespace XTMALL
  pctfree 10
  initrans 2
  maxtrans 255;

spool off

--2013��10��22�� 09:31:10 �ύ
--�û�������������������������֮�������ʺ�
alter table ge_user_personal add loginFailedCount integer default 0;
--��ǰ�ʺ��Ƿ��ѱ�����
alter table ge_user_personal add lockUserAccount varchar(1) default 'N';
--�ʺ�����ʱ��
alter table ge_user_personal add lockTime timestamp;


