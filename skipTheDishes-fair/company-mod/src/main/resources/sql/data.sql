INSERT INTO PRODUCT (PRODUCT_ID, CODE, NAME, DESCRIPTION, ENABLED)
VALUES (1, '01', 'Bicycle', 'Evoke Cannibal', TRUE);
INSERT INTO PRODUCT (PRODUCT_ID, CODE, NAME, DESCRIPTION, ENABLED)
VALUES (2, '02', 'MacBook Pro', 'Full version', TRUE);

INSERT INTO ORDERS (CODE, ORDER_ID, STATUS, ENABLED)
VALUES ('01', 1, 'OPEN', TRUE );

INSERT INTO ORDER_PRODUCT (ID, ORDER_ID, PRODUCT_ID)
VALUES (1, 1, 1);

INSERT INTO APP_USER (USER_ID, USER_NAME, PWD, ROLES, ENABLED)
VALUES (1, 'user123', 'pwd', 'USER', TRUE);