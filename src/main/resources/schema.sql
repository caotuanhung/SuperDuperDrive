CREATE TABLE IF NOT EXISTS tbl_user (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(20) UNIQUE,
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS tbl_note (
    id INT PRIMARY KEY auto_increment,
    title VARCHAR(20),
    description VARCHAR (1000),
    user_id INT,
    foreign key (user_id) references tbl_user(id)
);

CREATE TABLE IF NOT EXISTS tbl_file (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    content_type VARCHAR,
    size VARCHAR,
    user_id INT,
    data BLOB,
    foreign key (user_id) references tbl_user(id)
);

CREATE TABLE IF NOT EXISTS tbl_credential (
    id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    user_id INT,
    foreign key (user_id) references tbl_user(id)
);

INSERT INTO tbl_user (username, salt, password, firstname, lastname) VALUES
    ('hungct', '4uVaNLEzAHSaCAYrHsfftg==', '1o4IBGpZ+4BsP82JZj0jASV4x7gYXRvObvtIWZ/Ppc/9SOLFxZzRaRzTsgtYhHoMZSRwV4NS7steDpGxpikPMwbsY0hY7f1gYUtf7gftUrRYxzd8+zICElK64EN4n8QyfEeNWe+ZzEtw5vP1yALsvXaxzYfIEwBlXzZnSOUfyBRB8jeNKe/FZDt1Zd5rh5Wyr+Vb5i0iT0ai7lr8PP2k2tKo2gxlPaZkO1G9YR0bmpVJVfCtIhUyLBhV+Gxkbuv7kvAE/lcW0tF/RJpAM+g6V3mpa9Wo+lY9WtU6plsxbo2SKfJeeTbRf5aPnGXXLp9XfUZcM2pBrUHBHFsf6JbhZA==', 'Hung', 'Cao'),
    ('dungdp', 'AEklbQqzxbiNQVGc3ibAog==', 'FsME+TVAuQcjZB5Mhpc8jsxSQ9gu4x57RoUkfZF/CnmwkFoC0A7X8uY654gsPWF239hVTT6/+nJp02F2TrFAK7OaJL4x+BFNnsMUd+KWEFnlP+NL2gNkBJaouu0S5VP/kyrOyX65ni5jZm8fLs4z5CEyBcDNbhcVCq1Wz7xq4L7mxNso5ZITuma/i0rTUgePFa7qQ4Dz704p1dMNON8EHhF3m06gG5YC6rmHBwLbyo9EuOQ/z4eukTvFL6yQMbEEAGGUZzvtETIgFWaRpKasDCTTlIX+bIC7LUvcQuNPvJjUOGLJz50uw5Xdh1veUiCHYXcCFoaYkxWvbjbf4UOgIQ==', 'Dung', 'Duong');

INSERT INTO tbl_note (title, description, user_id) VALUES
    ('Title 1', 'Description 1', 1),
    ('Title 2', 'Description 2', 1),
    ('Title 1', 'Description 1', 2),
    ('Title 2', 'Description 2', 2);

INSERT INTO tbl_credential (url, username, password, key, user_id) VALUES
    ('https://youtube.com', 'hungct_youtube', 'rNKc6t2PRrC4M1cFTr6G1Q==', 'J6D7JiJ8Oqi4DSol6zcbKQ==', 1),
    ('https://google.com', 'hungct_google', 'LNjWMp9duq8cKNP+8zyuvw==', 'EnDV9zlQKxY8kqJl+bLv/Q==', 1);


