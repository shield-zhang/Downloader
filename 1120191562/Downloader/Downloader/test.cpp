#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <cstring>
#include <windows.h>
#include<string>
#include <wininet.h> 
#pragma comment( lib,"Urlmon.lib")
#pragma comment( lib, "wininet.lib") 
#define MAXBLOCKSIZE 1024
using namespace std;
/**-----download(const char *Url,const char *save_as)--------------------
函数功能：将指定的Url地址的文件下载到本地
函数参数：
		Url：文件所指向的网络地址
		save_as：文件保存到本地的路径地址
-------------------------------------------------------------------------**/
void download(const char* Url, const char* save_as)/*将Url指向的地址的文件下载到save_as指向的本地文件*/
{
	byte Temp[MAXBLOCKSIZE];
	ULONG Number = 1;

	FILE* stream;
	HINTERNET hSession = InternetOpen("RookIE/1.0", INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, 0);
	if (hSession != NULL)
	{
		HINTERNET handle2 = InternetOpenUrl(hSession, Url, NULL, 0, INTERNET_FLAG_DONT_CACHE, 0);
		if (handle2 != NULL)
		{


			if ((fopen_s(&stream, save_as, "wb")) == 0)
			{
				while (Number > 0)
				{
					InternetReadFile(handle2, Temp, MAXBLOCKSIZE - 1, &Number);

					fwrite(Temp, sizeof(char), Number, stream);
				}
				fclose(stream);
			}

			InternetCloseHandle(handle2);
			handle2 = NULL;
		}
		InternetCloseHandle(hSession);
		hSession = NULL;
	}
}
int main(int argc, char* argv[]) {

	while (true)
	{
		char urlPath[MAX_PATH];
		string downloadPath = argv[0];
		string directory;
		int temp = downloadPath.rfind('\\');
		directory = downloadPath.substr(0, temp + 1);
		char fileName[MAX_PATH];
		printf_s("文件将保存在当前目录下\n");
		printf_s("输入URL(即所要下载文件的网址):");
		scanf_s("%s", urlPath);
		printf("\n");
		printf_s("输入要保存的文件名:");
		scanf_s("%s", fileName);
		directory = directory + fileName;
		printf("\n");
		download(urlPath, directory.data());
	}

}
