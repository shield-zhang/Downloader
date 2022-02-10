# 个人项目-并发文件下载助手

## 零、任务

实现一个可以并发下载文件的软件。

## 一、需求：

### 第1阶段 实现单个文件的下载功能

可以通过参数指定要下载的文件地址，下载的并发线程数（默认线程数为8），存放文件的地址（默认为当前目录）。

包括以下参数：

```
--url URL, -u URL                URL to download
--output filename, -o filename   Output filename
--concurrency number, -n number  Concurrency number (default: 8)
```

注意：并不是所有网站都支持多线程并发下载，需要先进行检测。

## 二、PSP表格： 

### 第1阶段 实现单个文件的下载功能
| PSP2.1 | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
| --- | --- | --- | --- |
| Planning | 计划 | 30               | 30               |
| · Estimate | · 估计这个任务需要多少时间 | 1200 | 2100             |
| Development | 开发 | 900 | 1800 |
| · Analysis | · 需求分析 (包括学习新技术) | 300 | 600 |
| · Design Spec | · 生成设计文档 | 100 | 100 |
| · Design Review | · 设计复审 (和同事审核设计文档) | 10 | 20 |
| · Coding Standard | · 代码规范 (为目前的开发制定合适的规范) | 30 | 20 |
| · Design | · 具体设计 | 60 | 200 |
| · Coding | · 具体编码 | 400 | 1000 |
| · Code Review | · 代码复审 |  |  |
| · Test | · 测试（自我测试，修改代码，提交修改） |  |  |
| Reporting | 报告 |  |  |
| · Test Report | · 测试报告 |  |  |
| · Size Measurement | · 计算工作量 |  |  |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划 |  |  |
|  | 合计 |  |  |

```

```

## 三、思路

### 第1阶段 实现单个文件的下载功能

第一阶段的要求是，输入url，保存路径，线程数目后实现多线程下载。

大体思路是根据url，获取下载文件的大小。然后根据线程数目，算出来每个线程所要下载的文件块大小。当每个线程负责的文件块下载完毕后，对所有文件按照次序进行合并。

主要用到多线程（线程池），http，IO流等方面的知识。

## 四、设计实现过程

### 第1阶段 实现单个文件的下载功能
