CREATE TABLE num (i int);-- 创建一个表用来储存0-9的数字
INSERT INTO num (i) VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);-- 生成0-9的数字，方便以后计算时间

CREATE TABLE  if not exists my_date(`date` date); -- 生成一个存储日期的表，date是字段名

-- 这里是生成并插入日期数据
INSERT INTO my_date (date) SELECT
    adddate(
        (   -- 这里的起始日期，你可以换成当前日期
            DATE_FORMAT("2018-07-27", '%Y-%m-%d')
        ),
        numlist.id
    ) AS `date`
FROM
    (
        SELECT
            n1.i + n10.i * 10 + n100.i * 100 + n1000.i * 1000+ n10000.i * 10000 AS id
        FROM
            num n1
        CROSS JOIN num AS n10
        CROSS JOIN num AS n100
        CROSS JOIN num AS n1000
        CROSS JOIN num AS n10000
    ) AS numlist;