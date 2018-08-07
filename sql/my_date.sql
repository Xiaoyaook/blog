-- 时间表，用来流量统计时，给无流量的日子置零
CREATE TABLE `my_date` (
  `date` date NOT NULL,
  PRIMARY KEY (`date`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO my_date (date) VALUES (20180725);

-- 当前月的数据
select DATE_FORMAT(md.date,'%Y-%m-%d') d,count(s.id) c from my_date md
left join sys_log s on DATE_FORMAT(md.date,'%Y-%m-%d') = DATE_FORMAT(s.create_by,'%Y-%m-%d')
where DATE_FORMAT(md.date,'%Y-%m') = DATE_FORMAT(curdate(),'%Y-%m') group by d;
