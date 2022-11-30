insert into users
(id, user_id, name, password)
values
(1, 'admin', 'username1', '$2a$12$/azTf0n4z.SWaS4cqK0apeXKizT36OtJuWnliiFm4i7sn0TM.a4Ym'),
(2, 'manager', 'username2', '$2a$12$/azTf0n4z.SWaS4cqK0apeXKizT36OtJuWnliiFm4i7sn0TM.a4Ym'),
(3, 'user', 'username3', '$2a$12$/azTf0n4z.SWaS4cqK0apeXKizT36OtJuWnliiFm4i7sn0TM.a4Ym');
-- password: 1234
commit;

insert into user_role
(id, role, user_id)
values
(1, 'ROLE_USER', 1),
(2, 'ROLE_MANAGER', 1),
(3, 'ROLE_ADMIN', 1),
(4, 'ROLE_USER', 2),
(5, 'ROLE_MANAGER', 2),
(6, 'ROLE_USER', 3);