drop table if exists books;

create table books (
    isbn_13 varchar (13) references books(isbn_13),
    tags varchar (100),
    primary key (isbn_13, tags)
);

insert into books_tags values (
    '1111111111111',          	
  'Adventure' 
)

