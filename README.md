# 个人项目-并发文件下载助手

## 零、任务

实现一个可以并发下载文件的软件。

## 一、要求

1. 阅读个人软件开发流程（PSP）的相关资料。
2. 可选的语言包括:C++，Java，Python。运行环境为64bit Windows 10。
3. 提交的代码要求经过代码质量分析工具的分析并消除所有的警告。如[Code Quality Analysis](http://msdn.microsoft.com/en-us/library/dd264897.aspx)。
4. 完成项目的首个版本之后，请使用**性能分析工具**来找出代码中的性能瓶颈并进行改进。
5. 使用单元测试对项目进行测试，并查看测试分支覆盖率等指标；并写出至少10个测试用例确保你的程序能够正确处理各种情况。如[Studio Profiling Tools](https://msdn.microsoft.com/en-us/library/mt210448.aspx)。
6. 使用Git来管理源代码和测试用例，**代码有进展即签入本地仓库，定期推送到服务器上，并发送合并请求提交每周的工作成果。签入记录不合理的项目会被抽查询问项目细节。**
7. 按照要求发布博客，结合个人项目的实践经历，撰写解决项目的心路历程与收获。**博客与项目明显不符的作业将取消作业成绩。**

> 注意：要求3、4、5根据所选编程语言使用对应的开发工具来完成。

## 二、需求：

分3个阶段完成，每周更新。

### 第1阶段 实现单个文件的下载功能

可以通过参数指定要下载的文件地址，下载的并发线程数（默认线程数为8），存放文件的地址（默认为当前目录）。

包括以下参数：

```
--url URL, -u URL                URL to download
--output filename, -o filename   Output filename
--concurrency number, -n number  Concurrency number (default: 8)
```

注意：并不是所有网站都支持多线程并发下载，需要先进行检测。

### 第2阶段 实现批量多协议文件下载功能

指定下载地址时

- 可以在参数中指定多个要下载的文件地址，即允许使用多个url参数
- 可以将这些地址放入一个文本文件中，指定从该文件中读取。参数
```
--input filename, -i filename filename with multiple URL
```
- 支持使用正则表达式来生成多个下载地址，

同时，除支持http/https协议外，也能够支持如ftp, bittorrent，磁力链等。

### 第3阶段 用户界面

目前的下载助手只有命令行版本，使用起来不是很方便，可以给用户提供GUI（图形用户界面）。至少提供三个功能：

- 添加下载链接：单个或多个、批量生成
- 可自动从系统剪贴板得到下载链接
- 下载设置：包括并发数、输出位置，并可保存设置信息，在下次启动应用时可自动加载

## 三、博客撰写要求：

在个人目录下，使用Markdown编写个人博客，建议每周至少发布一次。博客共15分，具体要求如下：
* 在开始实现程序之前，在下述PSP表格记录下你估计将在程序的各个模块的开发上耗费的时间。（0.5‘）
* 解题思路描述。即刚开始拿到题目后，如何思考，如何找资料的过程。（3‘）
* 设计实现过程。设计包括代码如何组织，比如会有几个类，几个函数，他们之间关系如何，关键函数是否需要画出流程图？单元测试是怎么设计的？（5‘）
* 记录在改进程序性能上所花费的时间，描述你改进的思路，并展示一张性能分析图（如使用Visual Studio的性能分析工具自动生成），并展示你程序中消耗最大的函数。（3‘）
* 代码说明。展示出项目关键代码，并解释思路与注释说明。（3‘）
* 在你实现完程序之后，在下述PSP表格记录下你在程序的各个模块上实际花费的时间。（0.5‘）

*附：PSP 2.1表格*

| PSP2.1 | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
| --- | --- | --- | --- |
| Planning | 计划 |  |  |
| · Estimate | · 估计这个任务需要多少时间 |  |  |
| Development | 开发 |  |  |
| · Analysis | · 需求分析 (包括学习新技术) |  |  |
| · Design Spec | · 生成设计文档 |  |  |
| · Design Review | · 设计复审 (和同事审核设计文档) |  |  |
| · Coding Standard | · 代码规范 (为目前的开发制定合适的规范) |  |  |
| · Design | · 具体设计 |  |  |
| · Coding | · 具体编码 |  |  |
| · Code Review | · 代码复审 |  |  |
| · Test | · 测试（自我测试，修改代码，提交修改） |  |  |
| Reporting | 报告 |  |  |
| · Test Report | · 测试报告 |  |  |
| · Size Measurement | · 计算工作量 |  |  |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划 |  |  |
|  | 合计 |  |  |

## 四、评分规则

个人项目分数由三部分组成，分别是
* 博客 — 15分，分数组成在博文规范中。
* 程序 — 35分

```
10分为源代码管理评分，该评分主要通过源代码管理中的commit注释信息，增量修改的内容，是否有运行说明等给分。
20分为正确性评分，正确性测试中输入范围限制在 1-1000，要求程序在 60 s 内给出结果，超时则认定运行结果无效。
5分为性能评分，性能测试中输入范围限制在 10000-1000000，没有时间的最小要求限制。
当程序的正确性评分等于20分时才可以参与性能评分环节，所以请各位同学务必保证自己程序的正确性。
```

* 注意事项：

```
按时间完成并提交——正常评分
晚交一周以内——折扣90%
晚交一周以上——折扣70%
不交或抄袭——0分【严禁代码与博客等一切形式的抄袭！请各位同学千万不要触碰底线，勿谓言之不预也！】
```
