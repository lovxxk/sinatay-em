-- drop sequence
drop sequence POLICYSERIALNUMBERSEQUENCE;
-- Create sequence 
create sequence POLICYSERIALNUMBERSEQUENCE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;