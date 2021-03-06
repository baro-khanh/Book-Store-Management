USE [master]
GO
/****** Object:  Database [LAB2]    Script Date: 11/28/2019 11:55:58 PM ******/
CREATE DATABASE [LAB2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LAB2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\LAB2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LAB2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\LAB2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LAB2] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LAB2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LAB2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LAB2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LAB2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LAB2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LAB2] SET ARITHABORT OFF 
GO
ALTER DATABASE [LAB2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LAB2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LAB2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LAB2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LAB2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LAB2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LAB2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LAB2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LAB2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LAB2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LAB2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LAB2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LAB2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LAB2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LAB2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LAB2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LAB2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LAB2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LAB2] SET  MULTI_USER 
GO
ALTER DATABASE [LAB2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LAB2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LAB2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LAB2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LAB2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LAB2] SET QUERY_STORE = OFF
GO
USE [LAB2]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[RoleID] [int] NOT NULL,
	[Available] [bit] NOT NULL,
	[Email] [nvarchar](max) NULL,
	[Phone] [nvarchar](20) NULL,
	[Fullname] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ApplyDiscount]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ApplyDiscount](
	[ApplyID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [nvarchar](50) NOT NULL,
	[DiscountCode] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ApplyDiscount] PRIMARY KEY CLUSTERED 
(
	[ApplyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[BookID] [int] IDENTITY(1,1) NOT NULL,
	[Status] [bit] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[Image] [nvarchar](max) NOT NULL,
	[Description] [nvarchar](500) NULL,
	[Price] [float] NOT NULL,
	[Author] [nvarchar](50) NOT NULL,
	[CateID] [int] NOT NULL,
	[ImportDate] [date] NOT NULL,
 CONSTRAINT [PK_tbl_Book] PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CateID] [int] IDENTITY(1,1) NOT NULL,
	[CateName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountCode] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](50) NOT NULL,
	[Amount] [int] NULL,
	[ImportDate] [date] NOT NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[BookID] [int] NOT NULL,
	[Amount] [int] NOT NULL,
	[SubPrice] [float] NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 11/28/2019 11:55:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [nvarchar](50) NOT NULL,
	[Total] [float] NOT NULL,
	[Datetime] [date] NOT NULL,
 CONSTRAINT [PK_tbo_Order] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([UserID], [Password], [RoleID], [Available], [Email], [Phone], [Fullname]) VALUES (N'baokhanh', N'123', 2, 1, N'100@gmail.com', N'1', N'baokhanh')
INSERT [dbo].[Account] ([UserID], [Password], [RoleID], [Available], [Email], [Phone], [Fullname]) VALUES (N'test', N'test', 1, 1, N'test@gmail.com', N'1111111111', N'test')
SET IDENTITY_INSERT [dbo].[Book] ON 

INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (6, 1, 5, N'Blink: The Power of Thinking Without Thinking', N'14-the-subtle-art-of-not-giving-a-fuck.u5387.d20170703.t220618.445788.jpg', N'How do we make decisions--good and bad--and why are some people so much better at it than others? That''s the question Malcolm Gladwell asks and answers in BLINK.', 130000, N'Malcolm Gladwell', 2, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (7, 1, 14, N'Frankenstein', N'72131049_518750112053732_3381039049010577408_o.jpg', N'Since it was first published in 1818, Mary ShelleyÃÂÃÂ¢ÃÂÃÂÃÂÃÂs seminal novel has generated countless print, stage and screen adaptations, but none has ever matched the power and philosophical resonance of the original. Composed as part of a challenge with Byron and Shelley to conjure up the most terrifying ghost story, Frankenstein narrates the chilling tale of a being created by a bright young scientist and the catastrophic consequences that ensue.', 75000, N'Mary Shelley', 2, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (11, 1, 11, N'Oxford Picture Dictionary English', N'1ffcb8e69daa9f1c09816323359595f4.jpg', N'Oxford Picture Dictionary English/Vietnamese 3 Ed. Dictionary', 259600, N'Norma Shapiro, Jayme Adelson', 1, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (12, 1, 0, N'1', N'D:\SE\Code\java\school\java_lab\labCN5\BUIDOBAOKHANH_LAB02\web\img\1ffcb8e69daa9f1c09816323359595f4.jpg', N'11', 1, N'1', 1, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (13, 0, 20, N'2', N'14-the-subtle-art-of-not-giving-a-fuck.u5387.d20170703.t220618.445788.jpg', N'test', 222000, N'test', 3, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (14, 1, 30, N'33', N'45632f497052dbac6098e306b47ee1f9.jpg', N'33', 3, N'3', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (15, 0, 3, N'4', N'14-the-subtle-art-of-not-giving-a-fuck.u5387.d20170703.t220618.445788.jpg', N'4', 4, N'4', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (16, 1, 4, N'5', N'45632f497052dbac6098e306b47ee1f9.jpg', N'5', 5, N'5', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (17, 0, 5, N'5', N'45632f497052dbac6098e306b47ee1f9.jpg', N'5', 5, N'5', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (18, 0, 5, N'5', N'45632f497052dbac6098e306b47ee1f9.jpg', N'5', 5, N'5', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (19, 0, 5, N'5', N'45632f497052dbac6098e306b47ee1f9.jpg', N'5', 5, N'5', 1, CAST(N'2019-11-25' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (20, 0, 1, N'test', N'75380405_518750185387058_3501572564528922624_o.jpg', N'', 100000, N'test', 2, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (21, 1, 9, N'test', N'75380405_518750185387058_3501572564528922624_o.jpg', N'hihi', 100000, N'test', 1, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (22, 1, 89, N'test1', N'14-the-subtle-art-of-not-giving-a-fuck.u5387.d20170703.t220618.445788.jpg', N'haha', 100000, N'test1', 3, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Book] ([BookID], [Status], [Quantity], [Title], [Image], [Description], [Price], [Author], [CateID], [ImportDate]) VALUES (23, 1, 11, N'About Us', N'45632f497052dbac6098e306b47ee1f9.jpg', N'hay', 150000, N'Christin', 1, CAST(N'2019-11-28' AS Date))
SET IDENTITY_INSERT [dbo].[Book] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (1, N'Romance')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (2, N'Shoujo')
INSERT [dbo].[Category] ([CateID], [CateName]) VALUES (3, N'Harem')
SET IDENTITY_INSERT [dbo].[Category] OFF
INSERT [dbo].[Discount] ([DiscountCode], [Description], [Amount], [ImportDate]) VALUES (N'GOODBYE', N'HIHI', 30, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Discount] ([DiscountCode], [Description], [Amount], [ImportDate]) VALUES (N'HAPPYLAB', N'PASS', 100, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Discount] ([DiscountCode], [Description], [Amount], [ImportDate]) VALUES (N'WELCOME', N'20/11', 10, CAST(N'2019-11-28' AS Date))
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (2, N'user')
SET IDENTITY_INSERT [dbo].[Role] OFF
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[ApplyDiscount]  WITH CHECK ADD  CONSTRAINT [FK_ApplyDiscount_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[ApplyDiscount] CHECK CONSTRAINT [FK_ApplyDiscount_Account]
GO
ALTER TABLE [dbo].[ApplyDiscount]  WITH CHECK ADD  CONSTRAINT [FK_ApplyDiscount_Discount] FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[Discount] ([DiscountCode])
GO
ALTER TABLE [dbo].[ApplyDiscount] CHECK CONSTRAINT [FK_ApplyDiscount_Discount]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Category] FOREIGN KEY([CateID])
REFERENCES [dbo].[Category] ([CateID])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_Category]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Book] FOREIGN KEY([BookID])
REFERENCES [dbo].[Book] ([BookID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Book]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_tbl_Order] FOREIGN KEY([OrderID])
REFERENCES [dbo].[tbl_Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_tbl_Order]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([UserID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_Account]
GO
USE [master]
GO
ALTER DATABASE [LAB2] SET  READ_WRITE 
GO
