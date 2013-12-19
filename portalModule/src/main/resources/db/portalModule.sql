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
  is '序号';
comment on column PORTALTRANSACTION.TRANSCODE
  is '交易代码';
comment on column PORTALTRANSACTION.BATCHID
  is '批量处理ID';
comment on column PORTALTRANSACTION.TRANSIDENTIFY
  is '交易标示';
comment on column PORTALTRANSACTION.TRANSSERIALNUMBER
  is '交易流水号';
comment on column PORTALTRANSACTION.MERCHANTTRANSSERIALNUMBER
  is '商家交易流水号';
comment on column PORTALTRANSACTION.ORDERSERIALNUMBER
  is '订单号';
comment on column PORTALTRANSACTION.MERCHANTORDERNUMBER
  is '商家订单号';
comment on column PORTALTRANSACTION.REQUESTURL
  is '请求URL';
comment on column PORTALTRANSACTION.CALLBACKURL
  is '回调URL';
comment on column PORTALTRANSACTION.REQUESTTIME
  is '请求时间';
comment on column PORTALTRANSACTION.RESPONSETIME
  is '应答时间';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUS
  is '请求处理状态';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUSNAME
  is '请求处理状态名称';
comment on column PORTALTRANSACTION.REQUESTPROCESSSTATUSDESC
  is '请求处理状态描述';
comment on column PORTALTRANSACTION.CREATETIME
  is '创建时间';
comment on column PORTALTRANSACTION.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column PORTALTRANSACTIONMESSAGE.SYSTEMNAME
  is '交互系统名称';
comment on column PORTALTRANSACTIONMESSAGE.MESSAGETYPE
  is '报文类型';
comment on column PORTALTRANSACTIONMESSAGE.TRANSACTIONMESSAGE
  is '交易报文';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUS
  is '处理状态';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUSNAME
  is '处理状态名称';
comment on column PORTALTRANSACTIONMESSAGE.PROCESSSTATUSDESC
  is '处理状态描述';
comment on column PORTALTRANSACTIONMESSAGE.TRANSACTIONSERIALNO
  is '报文交易';
comment on column PORTALTRANSACTIONMESSAGE.CREATETIME
  is '创建时间';
comment on column PORTALTRANSACTIONMESSAGE.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMCODE
  is '系统代码';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMNAME
  is '系统名称';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMSIMPLENAME
  is '系统简称';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMTYPE
  is '系统类型';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMDESC
  is '系统描述';
comment on column PORTAL_INTERFACE_SYSTEM.SYSTEMSUMMARY
  is '系统简介';
comment on column PORTAL_INTERFACE_SYSTEM.OPERATORID
  is '操作员';
comment on column PORTAL_INTERFACE_SYSTEM.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE_SYSTEM.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column PORTAL_INTERFACE.TRANSCODE
  is '交易代码';
comment on column PORTAL_INTERFACE.TRANSNAME
  is '交易名称';
comment on column PORTAL_INTERFACE.TRANSPORTTYPE
  is '数据传输类型';
comment on column PORTAL_INTERFACE.INTERFACETYPE
  is '接口类型';
comment on column PORTAL_INTERFACE.STATUS
  is '接口状态（1-启用
，0-停用）';
comment on column PORTAL_INTERFACE.SENDERID
  is '发送方ID';
comment on column PORTAL_INTERFACE.RECEIVERID
  is '接收方ID';
comment on column PORTAL_INTERFACE.REQUESTURL
  is '服务器请求地址URL';
comment on column PORTAL_INTERFACE.REQUESTECODING
  is '请求编码集';
comment on column PORTAL_INTERFACE.CALLBACKURL
  is '回调URL';
comment on column PORTAL_INTERFACE.PREFIX
  is '前缀';
comment on column PORTAL_INTERFACE.NAMESPACEURI
  is '命名空间';
comment on column PORTAL_INTERFACE.LOCALPART
  is '本地方法';
comment on column PORTAL_INTERFACE.ACTION
  is '行动';
comment on column PORTAL_INTERFACE.DOINPUT
  is '设置从URL连接读数据';
comment on column PORTAL_INTERFACE.DOOUTPUT
  is '设置将数据写入URL连接';
comment on column PORTAL_INTERFACE.CONNECTTIMEOUT
  is '连接超时时间';
comment on column PORTAL_INTERFACE.READTIMEOUT
  is '读超时';
comment on column PORTAL_INTERFACE.ENCAPSULATIONTYPE
  is '封装类型';
comment on column PORTAL_INTERFACE.RETURNTYPE
  is '返回类型';
comment on column PORTAL_INTERFACE.MESSAGEENCODING
  is '报文编码集';
comment on column PORTAL_INTERFACE.RECORDMESSAGETYPE
  is '记录报文类型';
comment on column PORTAL_INTERFACE.ENCRYPTIONTYPE
  is '报文加密类型';
comment on column PORTAL_INTERFACE.HANDLEMESSAGETYPE
  is '报文处理类型';
comment on column PORTAL_INTERFACE.SYSTEMSERIALNO
  is '系统';
comment on column PORTAL_INTERFACE.OPERATORID
  is '操作员';
comment on column PORTAL_INTERFACE.REMARK
  is '备注';
comment on column PORTAL_INTERFACE.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column PORTAL_INTERFACE_ACCOUNT.LOGINNAME
  is '登录名';
comment on column PORTAL_INTERFACE_ACCOUNT.PASSWORD
  is '密码';
comment on column PORTAL_INTERFACE_ACCOUNT.IPADDRESS
  is 'IP地址';
comment on column PORTAL_INTERFACE_ACCOUNT.PORT
  is '端口号';
comment on column PORTAL_INTERFACE_ACCOUNT.STATUS
  is '状态';
comment on column PORTAL_INTERFACE_ACCOUNT.OPERATORID
  is '操作员';
comment on column PORTAL_INTERFACE_ACCOUNT.INTERFACESERIALNO
  is '接口';
comment on column PORTAL_INTERFACE_ACCOUNT.SYSTEMSERIALNO
  is '系统';
comment on column PORTAL_INTERFACE_ACCOUNT.REMARK
  is '备注';
comment on column PORTAL_INTERFACE_ACCOUNT.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE_ACCOUNT.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTNAME
  is '元素名称';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTVALUE
  is '元素值';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTTYPE
  is '元素类型';
comment on column PORTAL_INTERFACE_ELEMENT.NODETYPE
  is '节点类型';
comment on column PORTAL_INTERFACE_ELEMENT.DONE
  is '操作（0-不做，1-做）';
comment on column PORTAL_INTERFACE_ELEMENT.NAMESPACEURI
  is '命名空间';
comment on column PORTAL_INTERFACE_ELEMENT.PREFIX
  is '前缀';
comment on column PORTAL_INTERFACE_ELEMENT.LINENUMBER
  is '行数';
comment on column PORTAL_INTERFACE_ELEMENT.ELEMENTORDER
  is '元素顺序';
comment on column PORTAL_INTERFACE_ELEMENT.PARENTSERIALNO
  is '父元素';
comment on column PORTAL_INTERFACE_ELEMENT.INTERFACESERIALNO
  is '接口';
comment on column PORTAL_INTERFACE_ELEMENT.OPERATORID
  is '操作员';
comment on column PORTAL_INTERFACE_ELEMENT.REMARK
  is '备注';
comment on column PORTAL_INTERFACE_ELEMENT.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE_ELEMENT.UPDATETIME
  is '更新时间';
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
  is '接口处理类';
comment on column PORTAL_INTERFACE_HANDLERCLASS.SERIALNO
  is '序号';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CLASSNAME
  is '处理类名称';
comment on column PORTAL_INTERFACE_HANDLERCLASS.METHODNAME
  is '处理方法';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CLASSDESC
  is '处理类描述';
comment on column PORTAL_INTERFACE_HANDLERCLASS.INTERFACESERIALNO
  is '接口';
comment on column PORTAL_INTERFACE_HANDLERCLASS.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE_HANDLERCLASS.UPDATETIME
  is '更新时间';
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
  is '处理方法参数';
comment on column PORTAL_INTERFACE_METHODPARAM.SERIALNO
  is '序号';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERNAME
  is '参数名称';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERCLASSNAME
  is '参数类名称';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERTYPE
  is '参数类型';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERTYPENAME
  is '参数类型名称';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERVALUE
  is '参数值';
comment on column PORTAL_INTERFACE_METHODPARAM.DEFAULTVALUE
  is '参数默认值';
comment on column PORTAL_INTERFACE_METHODPARAM.ENCODEDVALUE
  is '加密值';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERMODE
  is '参数模式';
comment on column PORTAL_INTERFACE_METHODPARAM.PARAMETERORDER
  is '参数顺序';
comment on column PORTAL_INTERFACE_METHODPARAM.CLASSSERIALNO
  is '处理类';
comment on column PORTAL_INTERFACE_METHODPARAM.INTERFACESERIALNO
  is '接口信息';
comment on column PORTAL_INTERFACE_METHODPARAM.CREATETIME
  is '创建时间';
comment on column PORTAL_INTERFACE_METHODPARAM.UPDATETIME
  is '更新时间';
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
  is '序号';
comment on column TRANSACTIONTRACK.CUSTOMERID
  is '客户ID';
comment on column TRANSACTIONTRACK.ORDERSERIALNUMBER
  is '订单号';
comment on column TRANSACTIONTRACK.REQUESTURL
  is '请求URL';
comment on column TRANSACTIONTRACK.REQUESTMETHOD
  is '请求方式';
comment on column TRANSACTIONTRACK.ENCODED
  is '编码集';
comment on column TRANSACTIONTRACK.REQUESTSESSIONID
  is '请求Session Id';
comment on column TRANSACTIONTRACK.SERVICENAME
  is '服务名称';
comment on column TRANSACTIONTRACK.THREADID
  is '处理线程ID';
comment on column TRANSACTIONTRACK.SESSIONID
  is 'Session Id';
comment on column TRANSACTIONTRACK.COOKIEVALUE
  is 'cookie值';
comment on column TRANSACTIONTRACK.REQUESTTIME
  is '请求时间';
comment on column TRANSACTIONTRACK.RESPONSETIME
  is '应答时间';
comment on column TRANSACTIONTRACK.REMOTEHOST
  is '远程主机';
comment on column TRANSACTIONTRACK.REMOTEADDR
  is '远程主机地址';
comment on column TRANSACTIONTRACK.REMOTEPORT
  is '远程主机端口';
comment on column TRANSACTIONTRACK.LOCALNAME
  is '本地机器名称';
comment on column TRANSACTIONTRACK.LOCALADDR
  is '本地机器地址';
comment on column TRANSACTIONTRACK.LOCALPORT
  is '本地端口';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUS
  is '请求处理状态';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUSNAME
  is '请求处理状态名称';
comment on column TRANSACTIONTRACK.REQUESTPROCESSSTATUSDESC
  is '请求处理状态描述';
comment on column TRANSACTIONTRACK.CREATETIME
  is '创建时间';
comment on column TRANSACTIONTRACK.UPDATETIME
  is '更新时间';
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
