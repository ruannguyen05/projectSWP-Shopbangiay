USE [master]
GO

create database SWP391_Project_OnlineShop


CREATE TABLE [dbo].[Role](
[RoleId][int]IDENTITY(1,1) PRIMARY KEY NOT NULL,
[RoleName][nvarchar](50),
)
GO

CREATE TABLE [dbo].[Account](
	[Id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[Email] [varchar](50) NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [nvarchar](50) NULL,
	[Gender] [bit] NULL,
	[Phone] [varchar](10) NULL,
	[Address] [nvarchar](50) NULL,
	[LoginWith] [int] NULL,
	[Status] [bit] NULL,
	[LastDateLogin][date] NULL,
	[CreateDate][date] NULL,
	[UpdateDate][date] NULL,
	roleid INT FOREIGN KEY REFERENCES [dbo].[Role](RoleId)
) 

GO


 CREATE TABLE [dbo].[Categories](
	[cateid] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[Name] [varchar](50) NULL,
)
GO
CREATE TABLE ManagerProduct (
	[Id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
    aid INT FOREIGN KEY REFERENCES Account(Id),
    category_id INT FOREIGN KEY REFERENCES Categories(cateid)
);

	CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[name] [nvarchar](max) NULL,
	[price] [money] NULL,
	
	[description] [nvarchar](max) NULL,
	[imageUrl] [text] NULL,
	[quantitySaled] int null,
	categoryid INT FOREIGN KEY REFERENCES Categories(cateid),
	managerId INT FOREIGN KEY REFERENCES ManagerProduct(Id),
	[CreateDate][date] NULL,
	[UpdateDate][date] NULL,
	[Status] [bit] NULL,
) 


 create table [dbo].[Size](
 [Id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
 [size] int 
 )
 
 create table [dbo].[ProductSize](
 [pid] int NOT NULL,
 [sizeId] int not null,
 [quantity] int not null,
 Primary key (pid, sizeId),
 Foreign key (pid) references product(id),
 Foreign key (sizeId) references Size(id)
 )

 CREATE TABLE [dbo].[Order](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[AccountID] [int] FOREIGN KEY REFERENCES Account(Id),
	[ProductID] [int] FOREIGN KEY REFERENCES product(id),
	[size] [int] NOT NULL,
	[quantity] [int] NULL,
	[totalMoney] [money] NULL,
	[CreateDate] [date] not null
)

SET IDENTITY_INSERT [dbo].[Categories] ON 
 INSERT [dbo].[Categories] ([cateid], [Name]) VALUES (1, N'Adidas')
 INSERT [dbo].[Categories] ([cateid], [Name]) VALUES (2, N'Converse')
 INSERT [dbo].[Categories] ([cateid], [Name]) VALUES (3, N'Nike')
 INSERT [dbo].[Categories] ([cateid], [Name]) VALUES (4, N'Vans')
 INSERT [dbo].[Categories] ([cateid], [Name]) VALUES (5, N'Timberland')
 SET IDENTITY_INSERT [dbo].[Categories] OFF

 
 SET IDENTITY_INSERT [dbo].[Product] ON 
 go
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(1,1, N'Adidas ORIGINALS ADIFOM CLIMACOOL',2600000,N'Lorem',N'https://product.hstatic.net/200000025394/product/if3904_264_p2_6dfffdbe94ad424893754bf88c9886dc_master.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(2,1, N'Adidas NMD_V3',4200000,N'Lorem',N'https://product.hstatic.net/200000025394/product/gx2088_01_2093058ae66b4072bf544455869cad2e_master.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(3,1, N'Adidas ADIZERO SL', 3000000,N'Lorem',N'https://product.hstatic.net/200000025394/product/z4020605831493_b45dc35977dc64fbd2617313745bbe0d_38375dd6a960436ab833bcb6faac4aa9_master.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(4,1, N'Adidas GRAND COURT X LEGO® 2.0', 1900000,N'Lorem',N'https://product.hstatic.net/200000025394/product/giay_adidas_grand_court_x_lego_r__2.0_trang_gw7178_01_standard_a69964eb11cd451f83f2fa3c5fa7c3ce_master.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(5,1, N'Adidas HARDEN VOL.6', 4000000,N'Lorem',N'https://product.hstatic.net/200000025394/product/giay_harden_vol._6_trang_gw9031_01_standard_46e095d261ad43d1aefed313978d04e3_master.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(6,2, N'Converse 70s High Red', 1800000,N'Lorem',N'https://conversestore.vn/wp-content/uploads/2021/08/1-10.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(7,2, N'Converse Run Star Hike', 3200000,N'Lorem',N'https://conversestore.vn/wp-content/uploads/2021/11/z2901259446008_d3674ad8ddff3c232645d375bf3f41f3.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(8,2, N'Converse Classic Low All Black', 1200000,N'Lorem',N'https://conversestore.vn/wp-content/uploads/2021/08/1-5.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(9,2, N'Converse Chuck Taylor All Star Lift Outline Sketch', 1800000,N'Lorem',N'https://drake.vn/image/cache/catalog/Converse/GIA%CC%80Y%202/A05071C/A05071C_1-650x650.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(10,2, N'Cons Voltage Made it to The Top',3000000,N'Lorem',N'https://drake.vn/image/cache/catalog/Converse/GIA%CC%80Y%202/566166C/49-650x650.jpg',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(11,3, N'Nike Air Max SYSTM',2800000,N'Lorem',N'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/2ef928f1-b112-4e36-8a42-8f13a28440c7/air-max-systm-shoes-hLmQ85.png',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(12,3, N'Nike Air Jordan 1 Low G',4100000,N'Lorem',N'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/6475997d-e492-4dfa-932b-14457343e8d0/air-jordan-1-low-g-golf-shoes-8bKbqs.png',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(13,3, N'Nike Zoom Fly 5',4700000,N'Lorem',N'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/43be8545-4e48-45c2-8d49-ba3b8ef7342d/zoom-fly-5-road-running-shoes-lkx7Zp.png',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(14,3, N'Nike Air Force 1 07 Mid Fresh',4400000,N'Lorem',N'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/2da7d754-4a8b-4fc3-bce1-cf80c2cbd2d9/air-force-1-07-mid-fresh-shoes-SJJm6G.png',100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(15,3, N'Jordan Stadium 90',2800000,N'Lorem',N'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/7683c588-e0f5-46b9-9382-ef6ec80db821/jordan-stadium-90-shoes-Jn6ZH4.png',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(16,4, N'Vans SK8-Hi BW',1000000,N'Lorem',N'https://conversestore.vn/wp-content/uploads/2021/08/1-41.jpg', 100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(17,4, N'Vans Authen DX BW',2800000,N'Lorem',N'https://conversestore.vn/wp-content/uploads/2021/08/1-35.jpg',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(18,4, N'Vans AUTHENTIC CLASSIC ',1500000,N'Lorem',N'https://bizweb.dktcdn.net/thumb/1024x1024/100/140/774/products/vans-authentic-classic-black-black-vn000ee3bka-1.jpg',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(19,4, N'Vans ERA BMX ',1450000,N'Lorem',N'https://bizweb.dktcdn.net/thumb/1024x1024/100/140/774/products/giay-vans-era-bmx-white-vn0a4bv4v3h-1.jpg?v=1666024864323', 100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date), 1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(20,4, N'VANS CHECKERBOARD SLIP-ON CLASSIC',1455000,N'Lorem',N'https://bizweb.dktcdn.net/thumb/1024x1024/100/140/774/products/vans-slip-on-checkerboard-black-off-white-vn000eyebww-1.png',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(21,5, N'Timberland 6 Inch Premium Waterproof Boots',4100000,N'Lorem',N'https://authentic-shoes.com/wp-content/uploads/2023/04/10061_8840.png_84e01ed9f1b747fc8548e28cdd739cc8-768x564.png',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(22,5, N'Timberland Delphiville Textile ',2900000,N'Lorem',N'https://sneakerdaily.vn/wp-content/uploads/2023/08/Giay-Timberland-Delphiville-Textile-TB0A42HQL771-2-1.jpg' ,100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(23,5, N'Timberland Men’s Bradstreet Chukka Boot',3890000,N'Lorem',N'https://sneakerdaily.vn/wp-content/uploads/2023/08/Giay-Timberland-Mens-Bradstreet-Chukka-Boot-A146Q001-2-1.jpg',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(24,5, N'Timberland Men’s Seneca Bay Leathere Chukka',2100000,N'Lorem',N'https://www.tradeinn.com/f/13907/139075373_4/timberland-seneca-bay-leather-chukka-trainers.jpg', 100,CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
INSERT [dbo].[Product] ([id], [categoryid], [name],[price],[description],[imageUrl],[quantitySaled],[CreateDate],[UpdateDate],[Status])values(25,5, N'Timberland Solar Wave Low Fabric Medium',2800000,N'Lorem',N'https://sneakerdaily.vn/wp-content/uploads/2021/10/httpswww.footlocker.hkenptimberland-solar-wave-men-shoes-124199v314215084204-6-1.png',100, CAST (N'2023-09-16' as Date), CAST (N'2023-09-19' as Date),1)
go
SET IDENTITY_INSERT [dbo].[Product] OFF


--Size
go
insert Size values (36)
insert Size values (37)
insert Size values (38)
insert Size values (39)
insert Size values (40)
insert Size values (41)
insert Size values (42)
insert Size values (43)
insert Size values (44)
go

--ProductSize
go
insert ProductSize values (1, 1, 10)
insert ProductSize values (1, 2, 0)
insert ProductSize values (1, 3, 1)
insert ProductSize values (1, 4, 12)
insert ProductSize values (1, 5, 4)
insert ProductSize values (1, 6, 10)
insert ProductSize values (1, 7, 10)
insert ProductSize values (1, 8, 10)
insert ProductSize values (1, 9, 10)
insert ProductSize values (2, 1, 10)
insert ProductSize values (2, 2, 0)
insert ProductSize values (2, 3, 1)
insert ProductSize values (2, 4, 12)
insert ProductSize values (2, 5, 4)
insert ProductSize values (2, 6, 10)
insert ProductSize values (2, 7, 10)
insert ProductSize values (2, 8, 10)
insert ProductSize values (2, 9, 10)
insert ProductSize values (3, 1, 10)
insert ProductSize values (3, 2, 0)
insert ProductSize values (3, 3, 1)
insert ProductSize values (3, 4, 12)
insert ProductSize values (3, 5, 4)
insert ProductSize values (3, 6, 10)
insert ProductSize values (3, 7, 10)
insert ProductSize values (3, 8, 10)
insert ProductSize values (3, 9, 10)
insert ProductSize values (4, 1, 10)
insert ProductSize values (4, 2, 0)
insert ProductSize values (4, 3, 1)
insert ProductSize values (4, 4, 12)
insert ProductSize values (4, 5, 4)
insert ProductSize values (4, 6, 10)
insert ProductSize values (4, 7, 10)
insert ProductSize values (4, 8, 10)
insert ProductSize values (4, 9, 10)
insert ProductSize values (5, 1, 10)
insert ProductSize values (5, 2, 0)
insert ProductSize values (5, 3, 1)
insert ProductSize values (5, 4, 12)
insert ProductSize values (5, 5, 4)
insert ProductSize values (5, 6, 10)
insert ProductSize values (5, 7, 10)
insert ProductSize values (5, 8, 10)
insert ProductSize values (5, 9, 10)
insert ProductSize values (6, 1, 10)
insert ProductSize values (6, 2, 0)
insert ProductSize values (6, 3, 1)
insert ProductSize values (6, 4, 12)
insert ProductSize values (6, 5, 4)
insert ProductSize values (6, 6, 10)
insert ProductSize values (6, 7, 10)
insert ProductSize values (6, 8, 10)
insert ProductSize values (6, 9, 10)
insert ProductSize values (7, 1, 10)
insert ProductSize values (7, 2, 0)
insert ProductSize values (7, 3, 1)
insert ProductSize values (7, 4, 12)
insert ProductSize values (7, 5, 4)
insert ProductSize values (7, 6, 10)
insert ProductSize values (7, 7, 10)
insert ProductSize values (7, 8, 10)
insert ProductSize values (7, 9, 10)
insert ProductSize values (8, 1, 10)
insert ProductSize values (8, 2, 0)
insert ProductSize values (8, 3, 1)
insert ProductSize values (8, 4, 12)
insert ProductSize values (8, 5, 4)
insert ProductSize values (8, 6, 10)
insert ProductSize values (8, 7, 10)
insert ProductSize values (8, 8, 10)
insert ProductSize values (8, 9, 10)
insert ProductSize values (9, 1, 10)
insert ProductSize values (9, 2, 0)
insert ProductSize values (9, 3, 1)
insert ProductSize values (9, 4, 12)
insert ProductSize values (9, 5, 4)
insert ProductSize values (9, 6, 10)
insert ProductSize values (9, 7, 10)
insert ProductSize values (9, 8, 10)
insert ProductSize values (9, 9, 10)
insert ProductSize values (10, 1, 10)
insert ProductSize values (10, 2, 0)
insert ProductSize values (10, 3, 1)
insert ProductSize values (10, 4, 12)
insert ProductSize values (10, 5, 4)
insert ProductSize values (10, 6, 10)
insert ProductSize values (10, 7, 10)
insert ProductSize values (10, 8, 10)
insert ProductSize values (10, 9, 10)
insert ProductSize values (11, 1, 10)
insert ProductSize values (11, 2, 0)
insert ProductSize values (11, 3, 1)
insert ProductSize values (11, 4, 12)
insert ProductSize values (11, 5, 4)
insert ProductSize values (11, 6, 10)
insert ProductSize values (11, 7, 10)
insert ProductSize values (11, 8, 10)
insert ProductSize values (11, 9, 10)
insert ProductSize values (12, 1, 10)
insert ProductSize values (12, 2, 0)
insert ProductSize values (12, 3, 1)
insert ProductSize values (12, 4, 12)
insert ProductSize values (12, 5, 4)
insert ProductSize values (12, 6, 10)
insert ProductSize values (12, 7, 10)
insert ProductSize values (12, 8, 10)
insert ProductSize values (12, 9, 10)
insert ProductSize values (13, 1, 10)
insert ProductSize values (13, 2, 0)
insert ProductSize values (13, 3, 1)
insert ProductSize values (13, 4, 12)
insert ProductSize values (13, 5, 4)
insert ProductSize values (13, 6, 10)
insert ProductSize values (13, 7, 10)
insert ProductSize values (13, 8, 10)
insert ProductSize values (13, 9, 10)
insert ProductSize values (14, 1, 10)
insert ProductSize values (14, 2, 0)
insert ProductSize values (14, 3, 1)
insert ProductSize values (14, 4, 12)
insert ProductSize values (14, 5, 4)
insert ProductSize values (14, 6, 10)
insert ProductSize values (14, 7, 10)
insert ProductSize values (14, 8, 10)
insert ProductSize values (14, 9, 10)
insert ProductSize values (15, 1, 10)
insert ProductSize values (15, 2, 0)
insert ProductSize values (15, 3, 1)
insert ProductSize values (15, 4, 12)
insert ProductSize values (15, 5, 4)
insert ProductSize values (15, 6, 10)
insert ProductSize values (15, 7, 10)
insert ProductSize values (15, 8, 10)
insert ProductSize values (15, 9, 10)
insert ProductSize values (16, 1, 10)
insert ProductSize values (16, 2, 0)
insert ProductSize values (16, 3, 1)
insert ProductSize values (16, 4, 12)
insert ProductSize values (16, 5, 4)
insert ProductSize values (16, 6, 10)
insert ProductSize values (16, 7, 10)
insert ProductSize values (16, 8, 10)
insert ProductSize values (16, 9, 10)
insert ProductSize values (17, 1, 10)
insert ProductSize values (17, 2, 0)
insert ProductSize values (17, 3, 1)
insert ProductSize values (17, 4, 12)
insert ProductSize values (17, 5, 4)
insert ProductSize values (17, 6, 10)
insert ProductSize values (17, 7, 10)
insert ProductSize values (17, 8, 10)
insert ProductSize values (17, 9, 10)
insert ProductSize values (18, 1, 10)
insert ProductSize values (18, 2, 0)
insert ProductSize values (18, 3, 1)
insert ProductSize values (18, 4, 12)
insert ProductSize values (18, 5, 4)
insert ProductSize values (18, 6, 10)
insert ProductSize values (18, 7, 10)
insert ProductSize values (18, 8, 10)
insert ProductSize values (18, 9, 10)
insert ProductSize values (19, 1, 10)
insert ProductSize values (19, 2, 0)
insert ProductSize values (19, 3, 1)
insert ProductSize values (19, 4, 12)
insert ProductSize values (19, 5, 4)
insert ProductSize values (19, 6, 10)
insert ProductSize values (19, 7, 10)
insert ProductSize values (19, 8, 10)
insert ProductSize values (19, 9, 10)
insert ProductSize values (20, 1, 10)
insert ProductSize values (20, 2, 0)
insert ProductSize values (20, 3, 1)
insert ProductSize values (20, 4, 12)
insert ProductSize values (20, 5, 4)
insert ProductSize values (20, 6, 10)
insert ProductSize values (20, 7, 10)
insert ProductSize values (20, 8, 10)
insert ProductSize values (20, 9, 10)
insert ProductSize values (21, 1, 10)
insert ProductSize values (21, 2, 0)
insert ProductSize values (21, 3, 1)
insert ProductSize values (21, 4, 12)
insert ProductSize values (21, 5, 4)
insert ProductSize values (21, 6, 10)
insert ProductSize values (21, 7, 10)
insert ProductSize values (21, 8, 10)
insert ProductSize values (21, 9, 10)
insert ProductSize values (22, 1, 10)
insert ProductSize values (22, 2, 0)
insert ProductSize values (22, 3, 1)
insert ProductSize values (22, 4, 12)
insert ProductSize values (22, 5, 4)
insert ProductSize values (22, 6, 10)
insert ProductSize values (22, 7, 10)
insert ProductSize values (22, 8, 10)
insert ProductSize values (22, 9, 10)
insert ProductSize values (23, 1, 10)
insert ProductSize values (23, 2, 0)
insert ProductSize values (23, 3, 1)
insert ProductSize values (23, 4, 12)
insert ProductSize values (23, 5, 4)
insert ProductSize values (23, 6, 10)
insert ProductSize values (23, 7, 10)
insert ProductSize values (23, 8, 10)
insert ProductSize values (23, 9, 10)
insert ProductSize values (24, 1, 10)
insert ProductSize values (24, 2, 0)
insert ProductSize values (24, 3, 1)
insert ProductSize values (24, 4, 12)
insert ProductSize values (24, 5, 4)
insert ProductSize values (24, 6, 10)
insert ProductSize values (24, 7, 10)
insert ProductSize values (24, 8, 10)
insert ProductSize values (24, 9, 10)
insert ProductSize values (25, 1, 10)
insert ProductSize values (25, 2, 0)
insert ProductSize values (25, 3, 1)
insert ProductSize values (25, 4, 12)
insert ProductSize values (25, 5, 4)
insert ProductSize values (25, 6, 10)
insert ProductSize values (25, 7, 10)
insert ProductSize values (25, 8, 10)
insert ProductSize values (25, 9, 10)

go

--Role
go
insert Role values (N'Admin')
insert Role values (N'Customer')
insert Role values (N'Product Manager')
insert Role values (N'Marketing Manager')

go

--Account
go
insert Account values (N'haithhe176381@fpt.edu.vn', N'12345678', N'Trần Hoàng Hải', 1, '0972773693', N'Lạng Giang- Bắc Giang', 1, 1, '2023-09-16', '2023-09-16', '2023-09-16', 1)
insert Account values (N'a@gmail.com', N'12345678', N'Trần Trường An', 1, '0999999901', N'Hà Nội', 1, 1, '2023-09-19', '2023-09-19', '2023-09-19', 4)
insert Account values (N'b@gmail.com', N'12345678', N'Trần Minh Long', 1, '0999999902', N'Lạng Sơn', 1, 1, '2023-09-19', '2023-09-19', '2023-09-19', 4)
insert Account values (N'c@gmail.com', N'12345678', N'Trần Minh Vương', 1, '0999999903', N'Sài Gòn', 1, 1, '2023-09-19', '2023-09-19', '2023-09-19', 4)
insert Account values (N'd@gmail.com', N'12345678', N'Trương Gia Bình', 1, '0999999903', N'Quảng Nam', 1, 1, '2023-09-19', '2023-09-19', '2023-09-19', 4)
insert Account values (N'd@gmail.com', N'12345678', N'Nguyễn Xuân Phúc', 1, '0999999903', N'Quảng Nam', 1, 1, '2023-09-19', '2023-09-19', '2023-09-19', 4)
go

--ManagerProduct
go
insert ManagerProduct values (2, 1)
insert ManagerProduct values (3, 2)
insert ManagerProduct values (4, 3)
insert ManagerProduct values (5, 4)
insert ManagerProduct values (6, 5)
go

