INSERT INTO users (username,"password",enabled) VALUES
('guest_app','secret',true)
,('user','user',true)
,('admin','admin',true)
;


INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,"scope",authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
('guest_app','','secret','AUTHORIZED_READ,READ_ALL_GUESTS,WRITE_GUEST,UPDATE_GUEST','client_credentials',NULL,'ROLE_GUESTS_AUTHORIZED_CLIENT,ROLE_CLIENT',NULL,NULL,'{}','')
,('api_audit','','secret','AUTHORIZED_READ,READ_ALL_GUESTS','client_credentials',NULL,'ROLE_GUESTS_AUTHORIZED_CLIENT,ROLE_CLIENT',NULL,NULL,'{}','')
;

INSERT INTO authorities (username,authority) VALUES
('guest_app','ROLE_CLIENT')
,('guest_app','ROLE_GUESTS_AUTHORIZED_CLIENT')
,('user','ROLE_USER')
,('admin','ROLE_ADMIN')
,('admin','ROLE_USER')
;
