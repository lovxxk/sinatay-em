oracle���ݽű���
������ռ䡢�û�
create tablespace SINATAY 
datafile 'E:\oracle\product\10.2.0\oradata\orcl\SINATAY.dbf' 
size 4096M; 

create user sinatay identified by sinatay 
default tablespace SINATAY;

grant connect,resource,dba to sinatay;