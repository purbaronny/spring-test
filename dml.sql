INSERT INTO organization(code, name, parent_code) VALUES ('CE','Direktur Utama',NULL);
INSERT INTO organization(code, name, parent_code) VALUES ('CF','Direktur Keuangan','CE');
INSERT INTO organization(code, name, parent_code) VALUES ('CO','Direktur Operasional','CE');
INSERT INTO organization(code, name, parent_code) VALUES ('DB','Departemen Bisnis','CO');
INSERT INTO organization(code, name, parent_code) VALUES ('DD','Departemen Pengembangan','CO');

INSERT INTO "position"(code, name, organization_code) VALUES('CEO','Direktur Utama','CE');
INSERT INTO "position"(code, name, organization_code) VALUES('CFO','Direktur Keuangan','CF');
INSERT INTO "position"(code, name, organization_code) VALUES('COO','Direktur Operasional','CO');
INSERT INTO "position"(code, name, organization_code) VALUES('DB1','Departemen Bisnis 1','DB');
INSERT INTO "position"(code, name, organization_code) VALUES('DB2','Departemen Bisnis 2','DB');
INSERT INTO "position"(code, name, organization_code) VALUES('DD1','Departemen Pengembangan 1','DD');
INSERT INTO "position"(code, name, organization_code) VALUES('DD2','Departement Pengembangan 2','DD');

INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Bos Besar','CEO','/public/img/pic/ronny purba.jpg',NULL,1);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Bos Keuangan','CFO','/public/img/pic/ronny purba.jpg',1,2);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Bos Operasional','COO','/public/img/pic/ronny purba.jpg',1,3);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Bisnis 1.1','DB1','/public/img/pic/ronny purba.jpg',3,4);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Bisnis 1.2','DB1','/public/img/pic/ronny purba.jpg',3,5);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Bisnis 2.1','DB2','/public/img/pic/ronny purba.jpg',3,6);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Bisnis 2.2','DB2','/public/img/pic/ronny purba.jpg',3,7);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Pengembangan 1.1','DD1','/public/img/pic/ronny purba.jpg',3,8);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Pengembangan 1.2','DD1','/public/img/pic/ronny purba.jpg',3,9);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Pengembangan 2.1','DD2','/public/img/pic/ronny purba.jpg',3,10);
INSERT INTO employee(name, position_code, picture, report_to_id, id) VALUES('Manager Departemen Pengembangan 2.2','DD2','/public/img/pic/ronny purba.jpg',3,11);
