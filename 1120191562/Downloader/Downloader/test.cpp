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
�������ܣ���ָ����Url��ַ���ļ����ص�����
����������
		Url���ļ���ָ��������ַ
		save_as���ļ����浽���ص�·����ַ
-------------------------------------------------------------------------**/
void download(const char* Url, const char* save_as)/*��Urlָ��ĵ�ַ���ļ����ص�save_asָ��ı����ļ�*/
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
		printf_s("�ļ��������ڵ�ǰĿ¼��\n");
		printf_s("����URL(����Ҫ�����ļ�����ַ):");
		scanf_s("%s", urlPath);
		printf("\n");
		printf_s("����Ҫ������ļ���:");
		scanf_s("%s", fileName);
		directory = directory + fileName;
		printf("\n");
		download(urlPath, directory.data());
	}

}
