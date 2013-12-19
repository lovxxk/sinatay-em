---------------------------------------------
-- Export file for user SINATAY            --
-- Created by MACOSX on 2013-8-5, 22:43:05 --
---------------------------------------------

spool portalModule.log

prompt
prompt Creating table PORTALTRANSACTION
prompt ================================
prompt
create table PORTALTRANSACTION
(
  SERIALNO                  VARCHAR2(32) not null,
  TRANSCODE                 VARCHAR2(50),
  BATCHID                   VARCHAR2(50),
  TRANSIDENTIFY             VARCHAR2(50),
  TRANSSERIALNUMBER         VARCHAR2(50),
  MERCHANTTRANSSERIALNUMBER VARCHAR2(50),
  ORDERSERIALNUMBER         VARCHAR2(50),
  MERCHANTORDERNUMBER       VARCHAR2(50),
  REQUESTURL                VARCHAR2(500),
  CALLBACKURL               VARCHAR2(500),
  REQUESTTIME               TIMESTAMP(6),
  RESPONSETIME              TIMESTAMP(6),
  REQUESTPROCESSSTATUS      INTEGER,
  REQUESTPROCESSSTATUSNAME  VARCHAR2(50),
  REQUESTPROCESSSTATUSDESC  VARCHAR2(1000),
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
comment on column PORTALTRANSACTION.SERIALNO
  is '���';
comment on column PORTALTRANSACTION.TRANSCODE
  is '���״���';
comment on column PORTALTRANSACTION.BATCHID
  is '��������ID';
comment on column PORTALTRANSACTION.TRANSIDENTIFY
  is '���ױ�ʾ';
comment on column PORTALTRANSACTION.TRANSSERIALNUMBER
  is '������ˮ��';
comment on column PORTALTRANSACTION.MERCHANTTRANSSERIALNUMBER
  is '�̼ҽ�����ˮ��';
comment on column PORTALTRANSACTION.ORDERSERIALNUMBER
  is '������';
comment on column PORTALTRANSACTION.MERCHANTORDERNUMBER
  is '�̼Ҷ�����';
comment on column PORTALTRANSACTION.REQUESTURL
  is '����URL';
comment on column PORTALTRANSACTION.CALLBACKURL
  is '�ص�URL';
comment on column PORTALTRANSACTION.REQUESTTIME
  is '����ʱ��';
comment on column PORTALTRANSACTION.RESPONSETIME
  is 'Ӧ��ʱ��';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUS
  is '������״̬';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUSNAME
  is '������״̬����';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUSDESC
  is '������״̬����';
comment on column PORTALTRANSACTION.CREATETIME
  is '����ʱ��';
comment on column PORTALTRANSACTION.UPDATETIME
  is '����ʱ��';
alter table PORTALTRANSACTION
  add constraint PORTALTRANS_PK primary key (SERIALNO)
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
prompt Creating table PORTALTRANSACTIONMESSAGE
prompt =======================================
prompt
create table PORTALTRANSACTIONMESSAGE
(
  SERIALNO            VARCHAR2(32) not null,
  SYSTEMNAME          VARCHAR2(50),
  MESSAGETYPE         INTEGER,
  TRANSACTIONMESSAGE  CLOB,
  PROCESSSTATUS       INTEGER,
  PROCESSSTATUSNAME   VARCHAR2(50),
  PROCESSSTATUSDESC   VARCHAR2(1000),
  TRANSACTIONSERIALNO VARCHAR2(32),
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
comment on column PORTALTRANSACTIONMESSAGE.SERIALNO
  is '���';
comment on column PORTALTRANSACTIONMESSAGE.SYSTEMNAME
  is '����ϵͳ����';
comment on column PORTALTRANSACTIONMESSAGE.MESSAGETYPE
  is '��������';
comment on column PORTALTRANSACTIONMESSAGE.TRANSACTIONMESSAGE
  is '���ױ���';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUS
  is '����״̬';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUSNAME
  is '����״̬����';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUSDESC
  is '����״̬����';
comment on column PORTALTRANSACTIONMESSAGE.TRANSACTIONSERIALNO
  is '���Ľ���';
comment on column PORTALTRANSACTIONMESSAGE.CREATETIME
  is '����ʱ��';
comment on column PORTALTRANSACTIONMESSAGE.UPDATETIME
  is '����ʱ��';
alter table PORTALTRANSACTIONMESSAGE
  add constraint PORTALTRANSMSG_PK primary key (SERIALNO)
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
prompt Creating table PORTAL_INTERFACE_SYSTEM
prompt ======================================
prompt
create table PORTAL_INTERFACE_SYSTEM
(
  SERIALNO         VARCHAR2(32) not null,
  SYSTEMCODE       VARCHAR2(100),
  SYSTEMNAME       VARCHAR2(100),
  SYSTEMSIMPLENAME VARCHAR2(255),
  SYSTEMTYPE       INTEGER,
  SYSTEMDESC       VARCHAR2(500),
  SYSTEMSUMMARY    VARCHAR2(500),
  OPERATORID       VARCHAR2(32),
  CREATETIME       TIMESTAMP(6),
  UPDATETIME       TIMESTAMP(6)
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
comment on column PORTAL_INTERFACE_SYSTEM.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMCODE
  is 'ϵͳ����';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMNAME
  is 'ϵͳ����';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMSIMPLENAME
  is 'ϵͳ���';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMTYPE
  is 'ϵͳ����';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMDESC
  is 'ϵͳ����';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMSUMMARY
  is 'ϵͳ���';
comment on column PORTAL_INTERFACE_SYSTEM.OPERATORID
  is '����Ա';
comment on column PORTAL_INTERFACE_SYSTEM.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE_SYSTEM.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE_SYSTEM
  add constraint PORTAL_INTERFACELICATIONSYS_PK primary key (SERIALNO)
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
prompt Creating table PORTAL_INTERFACE
prompt ===============================
prompt
create table PORTAL_INTERFACE
(
  SERIALNO          VARCHAR2(32) not null,
  TRANSCODE         VARCHAR2(32),
  TRANSNAME         VARCHAR2(200),
  TRANSPORTTYPE     VARCHAR2(20),
  INTERFACETYPE     INTEGER,
  STATUS            INTEGER,
  SENDERID          VARCHAR2(50),
  RECEIVERID        VARCHAR2(50),
  REQUESTURL        VARCHAR2(500),
  REQUESTECODING    VARCHAR2(20),
  CALLBACKURL       VARCHAR2(500),
  PREFIX            VARCHAR2(255),
  NAMESPACEURI      VARCHAR2(500),
  LOCALPART         VARCHAR2(255),
  ACTION            VARCHAR2(255),
  DOINPUT           INTEGER,
  DOOUTPUT          INTEGER,
  CONNECTTIMEOUT    INTEGER,
  READTIMEOUT       INTEGER,
  ENCAPSULATIONTYPE INTEGER,
  RETURNTYPE        INTEGER,
  MESSAGEENCODING   VARCHAR2(20),
  RECORDMESSAGETYPE INTEGER,
  ENCRYPTIONTYPE    INTEGER,
  HANDLEMESSAGETYPE VARCHAR2(20),
  SYSTEMSERIALNO    VARCHAR2(32),
  OPERATORID        VARCHAR2(32),
  REMARK            VARCHAR2(255),
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
comment on column PORTAL_INTERFACE.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE.TRANSCODE
  is '���״���';
comment on column PORTAL_INTERFACE.TRANSNAME
  is '��������';
comment on column PORTAL_INTERFACE.TRANSPORTTYPE
  is '���ݴ�������';
comment on column PORTAL_INTERFACE.INTERFACETYPE
  is '�ӿ�����';
comment on column PORTAL_INTERFACE.STATUS
  is '�ӿ�״̬��1-����
��0-ͣ�ã�';
comment on column PORTAL_INTERFACE.SENDERID
  is '���ͷ�ID';
comment on column PORTAL_INTERFACE.RECEIVERID
  is '���շ�ID';
comment on column PORTAL_INTERFACE.REQUESTURL
  is '�����������ַURL';
comment on column PORTAL_INTERFACE.REQUESTECODING
  is '������뼯';
comment on column PORTAL_INTERFACE.CALLBACKURL
  is '�ص�URL';
comment on column PORTAL_INTERFACE.PREFIX
  is 'ǰ׺';
comment on column PORTAL_INTERFACE.NAMESPACEURI
  is '�����ռ�';
comment on column PORTAL_INTERFACE.LOCALPART
  is '���ط���';
comment on column PORTAL_INTERFACE.ACTION
  is '�ж�';
comment on column PORTAL_INTERFACE.DOINPUT
  is '���ô�URL���Ӷ�����';
comment on column PORTAL_INTERFACE.DOOUTPUT
  is '���ý�����д��URL����';
comment on column PORTAL_INTERFACE.CONNECTTIMEOUT
  is '���ӳ�ʱʱ��';
comment on column PORTAL_INTERFACE.READTIMEOUT
  is '����ʱ';
comment on column PORTAL_INTERFACE.ENCAPSULATIONTYPE
  is '��װ����';
comment on column PORTAL_INTERFACE.RETURNTYPE
  is '��������';
comment on column PORTAL_INTERFACE.MESSAGEENCODING
  is '���ı��뼯';
comment on column PORTAL_INTERFACE.RECORDMESSAGETYPE
  is '��¼��������';
comment on column PORTAL_INTERFACE.ENCRYPTIONTYPE
  is '���ļ�������';
comment on column PORTAL_INTERFACE.HANDLEMESSAGETYPE
  is '���Ĵ�������';
comment on column PORTAL_INTERFACE.SYSTEMSERIALNO
  is 'ϵͳ';
comment on column PORTAL_INTERFACE.OPERATORID
  is '����Ա';
comment on column PORTAL_INTERFACE.REMARK
  is '��ע';
comment on column PORTAL_INTERFACE.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE
  add constraint PORTAL_INTERFACE_PK primary key (SERIALNO)
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
alter table PORTAL_INTERFACE
  add constraint PORTAL_INTERFACE_SYS_UK unique (SYSTEMSERIALNO)
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
alter table PORTAL_INTERFACE
  add constraint PORTAL_INTERFACE_RT_SYS_FK foreign key (SYSTEMSERIALNO)
  references PORTAL_INTERFACE_SYSTEM (SERIALNO);

prompt
prompt Creating table PORTAL_INTERFACE_ACCOUNT
prompt =======================================
prompt
create table PORTAL_INTERFACE_ACCOUNT
(
  SERIALNO          VARCHAR2(32) not null,
  LOGINNAME         VARCHAR2(32),
  PASSWORD          VARCHAR2(200),
  IPADDRESS         VARCHAR2(255),
  PORT              VARCHAR2(255),
  STATUS            INTEGER,
  OPERATORID        VARCHAR2(32),
  INTERFACESERIALNO VARCHAR2(32),
  SYSTEMSERIALNO    VARCHAR2(32),
  REMARK            VARCHAR2(255),
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
comment on column PORTAL_INTERFACE_ACCOUNT.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE_ACCOUNT.LOGINNAME
  is '��¼��';
comment on column PORTAL_INTERFACE_ACCOUNT.PASSWORD
  is '����';
comment on column PORTAL_INTERFACE_ACCOUNT.IPADDRESS
  is 'IP��ַ';
comment on column PORTAL_INTERFACE_ACCOUNT.PORT
  is '�˿ں�';
comment on column PORTAL_INTERFACE_ACCOUNT.STATUS
  is '״̬';
comment on column PORTAL_INTERFACE_ACCOUNT.OPERATORID
  is '����Ա';
comment on column PORTAL_INTERFACE_ACCOUNT.INTERFACESERIALNO
  is '�ӿ�';
comment on column PORTAL_INTERFACE_ACCOUNT.SYSTEMSERIALNO
  is 'ϵͳ';
comment on column PORTAL_INTERFACE_ACCOUNT.REMARK
  is '��ע';
comment on column PORTAL_INTERFACE_ACCOUNT.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE_ACCOUNT.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE_ACCOUNT
  add constraint PTI_ACCOUNT_PK primary key (SERIALNO)
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
alter table PORTAL_INTERFACE_ACCOUNT
  add constraint PTI_ACCOUNT_RT_PTISYS_PK foreign key (SYSTEMSERIALNO)
  references PORTAL_INTERFACE_SYSTEM (SERIALNO);
alter table PORTAL_INTERFACE_ACCOUNT
  add constraint PTI_ACCOUNT_RT_PTI_PK foreign key (INTERFACESERIALNO)
  references PORTAL_INTERFACE (SERIALNO);

prompt
prompt Creating table PORTAL_INTERFACE_ELEMENT
prompt =======================================
prompt
create table PORTAL_INTERFACE_ELEMENT
(
  SERIALNO          VARCHAR2(32) not null,
  ELEMENTNAME       VARCHAR2(32),
  ELEMENTVALUE      VARCHAR2(200),
  ELEMENTTYPE       INTEGER,
  NODETYPE          INTEGER,
  DONE              INTEGER,
  NAMESPACEURI      VARCHAR2(500),
  PREFIX            VARCHAR2(255),
  LINENUMBER        INTEGER,
  ELEMENTORDER      INTEGER,
  PARENTSERIALNO    VARCHAR2(32),
  INTERFACESERIALNO VARCHAR2(32),
  OPERATORID        VARCHAR2(32),
  REMARK            VARCHAR2(255),
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
comment on column PORTAL_INTERFACE_ELEMENT.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTNAME
  is 'Ԫ������';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTVALUE
  is 'Ԫ��ֵ';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTTYPE
  is 'Ԫ������';
comment on column PORTAL_INTERFACE_ELEMENT.NODETYPE
  is '�ڵ�����';
comment on column PORTAL_INTERFACE_ELEMENT.DONE
  is '������0-������1-����';
comment on column PORTAL_INTERFACE_ELEMENT.NAMESPACEURI
  is '�����ռ�';
comment on column PORTAL_INTERFACE_ELEMENT.PREFIX
  is 'ǰ׺';
comment on column PORTAL_INTERFACE_ELEMENT.LINENUMBER
  is '����';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTORDER
  is 'Ԫ��˳��';
comment on column PORTAL_INTERFACE_ELEMENT.PARENTSERIALNO
  is '��Ԫ��';
comment on column PORTAL_INTERFACE_ELEMENT.INTERFACESERIALNO
  is '�ӿ�';
comment on column PORTAL_INTERFACE_ELEMENT.OPERATORID
  is '����Ա';
comment on column PORTAL_INTERFACE_ELEMENT.REMARK
  is '��ע';
comment on column PORTAL_INTERFACE_ELEMENT.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE_ELEMENT.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE_ELEMENT
  add constraint PTI_ELEMENT_PK primary key (SERIALNO)
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
alter table PORTAL_INTERFACE_ELEMENT
  add constraint PTI_ELEMENT_RT_PTI_FK foreign key (INTERFACESERIALNO)
  references PORTAL_INTERFACE (SERIALNO);

prompt
prompt Creating table PORTAL_INTERFACE_HANDLERCLASS
prompt ============================================
prompt
create table PORTAL_INTERFACE_HANDLERCLASS
(
  SERIALNO          VARCHAR2(32) not null,
  CLASSNAME         VARCHAR2(255),
  METHODNAME        VARCHAR2(255),
  CLASSDESC         VARCHAR2(255),
  INTERFACESERIALNO VARCHAR2(32),
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
comment on table PORTAL_INTERFACE_HANDLERCLASS
  is '�ӿڴ�����';
comment on column PORTAL_INTERFACE_HANDLERCLASS.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CLASSNAME
  is '����������';
comment on column PORTAL_INTERFACE_HANDLERCLASS.METHODNAME
  is '������';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CLASSDESC
  is '����������';
comment on column PORTAL_INTERFACE_HANDLERCLASS.INTERFACESERIALNO
  is '�ӿ�';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE_HANDLERCLASS.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE_HANDLERCLASS
  add constraint PTI_HANDLERCLASS_PK primary key (SERIALNO)
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
alter table PORTAL_INTERFACE_HANDLERCLASS
  add constraint PTI_HANDLERCLASS_INTERFACE_UK unique (INTERFACESERIALNO)
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
alter table PORTAL_INTERFACE_HANDLERCLASS
  add constraint PTI_HANDLERCLASS_RT_PTI_FK foreign key (INTERFACESERIALNO)
  references PORTAL_INTERFACE (SERIALNO);

prompt
prompt Creating table PORTAL_INTERFACE_METHODPARAM
prompt ===========================================
prompt
create table PORTAL_INTERFACE_METHODPARAM
(
  SERIALNO           VARCHAR2(32) not null,
  PARAMETERNAME      VARCHAR2(255),
  PARAMETERCLASSNAME VARCHAR2(255),
  PARAMETERTYPE      VARCHAR2(255),
  PARAMETERTYPENAME  VARCHAR2(255),
  PARAMETERVALUE     VARCHAR2(255),
  DEFAULTVALUE       VARCHAR2(255),
  ENCODEDVALUE       VARCHAR2(255),
  PARAMETERMODE      INTEGER,
  PARAMETERORDER     INTEGER,
  CLASSSERIALNO      VARCHAR2(32),
  INTERFACESERIALNO  VARCHAR2(32),
  CREATETIME         TIMESTAMP(6),
  UPDATETIME         TIMESTAMP(6)
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
comment on table PORTAL_INTERFACE_METHODPARAM
  is '����������';
comment on column PORTAL_INTERFACE_METHODPARAM.SERIALNO
  is '���';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERNAME
  is '��������';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERCLASSNAME
  is '����������';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERTYPE
  is '��������';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERTYPENAME
  is '������������';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERVALUE
  is '����ֵ';
comment on column PORTAL_INTERFACE_METHODPARAM.DEFAULTVALUE
  is '����Ĭ��ֵ';
comment on column PORTAL_INTERFACE_METHODPARAM.ENCODEDVALUE
  is '����ֵ';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERMODE
  is '����ģʽ';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERORDER
  is '����˳��';
comment on column PORTAL_INTERFACE_METHODPARAM.CLASSSERIALNO
  is '������';
comment on column PORTAL_INTERFACE_METHODPARAM.INTERFACESERIALNO
  is '�ӿ���Ϣ';
comment on column PORTAL_INTERFACE_METHODPARAM.CREATETIME
  is '����ʱ��';
comment on column PORTAL_INTERFACE_METHODPARAM.UPDATETIME
  is '����ʱ��';
alter table PORTAL_INTERFACE_METHODPARAM
  add constraint PTI_METHODPARAM_PK primary key (SERIALNO)
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
alter table PORTAL_INTERFACE_METHODPARAM
  add constraint PTI_METHODPARAM_RT_PTIC_FK foreign key (CLASSSERIALNO)
  references PORTAL_INTERFACE_HANDLERCLASS (SERIALNO);
alter table PORTAL_INTERFACE_METHODPARAM
  add constraint PTI_METHODPARAM_RT_PTI_FK foreign key (INTERFACESERIALNO)
  references PORTAL_INTERFACE (SERIALNO);

prompt
prompt Creating table TRANSACTIONTRACK
prompt ===============================
prompt
create table TRANSACTIONTRACK
(
  SERIALNO                 VARCHAR2(32) not null,
  CUSTOMERID               VARCHAR2(50),
  ORDERSERIALNUMBER        VARCHAR2(50),
  REQUESTURL               VARCHAR2(500),
  REQUESTMETHOD            INTEGER,
  ENCODED                  VARCHAR2(50),
  REQUESTSESSIONID         VARCHAR2(50),
  SERVICENAME              VARCHAR2(255),
  THREADID                 VARCHAR2(50),
  SESSIONID                VARCHAR2(50),
  COOKIEVALUE              VARCHAR2(255),
  REQUESTTIME              TIMESTAMP(6),
  RESPONSETIME             TIMESTAMP(6),
  REMOTEHOST               VARCHAR2(255),
  REMOTEADDR               VARCHAR2(500),
  REMOTEPORT               INTEGER,
  LOCALNAME                VARCHAR2(255),
  LOCALADDR                VARCHAR2(500),
  LOCALPORT                INTEGER,
  REQUESTPROCESSSTATUS     INTEGER,
  REQUESTPROCESSSTATUSNAME VARCHAR2(50),
  REQUESTPROCESSSTATUSDESC VARCHAR2(1000),
  CREATETIME               TIMESTAMP(6),
  UPDATETIME               TIMESTAMP(6)
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
comment on column TRANSACTIONTRACK.SERIALNO
  is '���';
comment on column TRANSACTIONTRACK.CUSTOMERID
  is '�ͻ�ID';
comment on column TRANSACTIONTRACK.ORDERSERIALNUMBER
  is '������';
comment on column TRANSACTIONTRACK.REQUESTURL
  is '����URL';
comment on column TRANSACTIONTRACK.REQUESTMETHOD
  is '����ʽ';
comment on column TRANSACTIONTRACK.ENCODED
  is '���뼯';
comment on column TRANSACTIONTRACK.REQUESTSESSIONID
  is '����Session Id';
comment on column TRANSACTIONTRACK.SERVICENAME
  is '��������';
comment on column TRANSACTIONTRACK.THREADID
  is '�����߳�ID';
comment on column TRANSACTIONTRACK.SESSIONID
  is 'Session Id';
comment on column TRANSACTIONTRACK.COOKIEVALUE
  is 'cookieֵ';
comment on column TRANSACTIONTRACK.REQUESTTIME
  is '����ʱ��';
comment on column TRANSACTIONTRACK.RESPONSETIME
  is 'Ӧ��ʱ��';
comment on column TRANSACTIONTRACK.REMOTEHOST
  is 'Զ������';
comment on column TRANSACTIONTRACK.REMOTEADDR
  is 'Զ��������ַ';
comment on column TRANSACTIONTRACK.REMOTEPORT
  is 'Զ�������˿�';
comment on column TRANSACTIONTRACK.LOCALNAME
  is '���ػ�������';
comment on column TRANSACTIONTRACK.LOCALADDR
  is '���ػ�����ַ';
comment on column TRANSACTIONTRACK.LOCALPORT
  is '���ض˿�';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUS
  is '������״̬';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUSNAME
  is '������״̬����';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUSDESC
  is '������״̬����';
comment on column TRANSACTIONTRACK.CREATETIME
  is '����ʱ��';
comment on column TRANSACTIONTRACK.UPDATETIME
  is '����ʱ��';
alter table TRANSACTIONTRACK
  add constraint TRANSACTIONTRACK_PK primary key (SERIALNO)
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


spool off
