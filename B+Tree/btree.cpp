#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <bitset>
#include <stack>
#include <stdio.h>
using namespace std;

const int zero = 0;

string* strSplit(string strOrigin, string strTok)
{
	int cutAt;
	int index = 0; 
	string* strResult = new string[5];

		while ((cutAt = strOrigin.find_first_of(strTok)) != strOrigin.npos)
		{
			if (cutAt > 0)
			{
				strResult[index++] = strOrigin.substr(0, cutAt);
			}
			strOrigin = strOrigin.substr(cutAt + 1);
		}

	if (strOrigin.length() > 0)
	{
		strResult[index++] = strOrigin.substr(0, cutAt);
	}
	return strResult;
}

struct split_node {
	int left;
	int key;
	int right;
};

vector<int> read_block(int num, int block_size, string file_name) { // block읽기

	vector<int>block;
	int a;

	ifstream in(file_name, std::ios::binary);

	in.seekg(20 + block_size * num, ios::beg);

	for (int i = 0; i < (block_size)/4; i++) {
		in.read((char*)(&a), 4);
		if (a != 0) block.push_back(a);
	}

	in.close();

	return block;
}

void write_block(int num, int block_size, string file_name, vector<int>block) { //block 출력

	int a;

	ofstream out(file_name, std::ios::binary|ios::app);

	out.seekp(20 + block_size * num, ios::beg);

	for (int i = 0; i < block.size(); i++) {
		out.write(reinterpret_cast<char*>(&block[i]), sizeof(block[i]));
	}
	for (int i = 0; i < ((block_size/4) - block.size()); i++) {
		out.write((char*)(&zero), sizeof(zero));
	}

	out.close();

	return;
}

class BTree {
public:

	vector<vector<int> >arr;
	int size; // 입력받은 크기
	int rootBID;
	int depth; // 깊이
	int block_size; // block에 들어가는 int개수
	int block_num; // block개수

	BTree() {
		size = 0;
		rootBID = 0;
		depth = 0;
		block_size = 0;
		block_num = 0;

	}

	split_node leaf_split(int num) {
		vector<int>imsi;
		int split_num = ((arr[num].size() + 2) / 4)*2;
		while ((arr[num].size() != split_num))
		{
			imsi.push_back(arr[num][split_num]);
			arr[num].erase(arr[num].begin() + split_num);
		}
		arr.push_back(imsi);
		arr[num].push_back(arr.size());

		split_node mysn;
		mysn.left = num + 1; mysn.right = arr.size(); mysn.key = imsi[0];

		imsi.clear();
		return mysn;
	}
	split_node non_leaf_split(int num) {
		vector<int>imsi;
		int split_num;
		split_node mysn;
		if (arr[num].size() % 4 == 3) {
			split_num = arr[num].size() / 2;
		}
		else {
			split_num = (arr[num].size() / 2) + 1;
		}
		mysn.key = arr[num][split_num];
		arr[num].erase(arr[num].begin() + split_num);
		
		while ((arr[num].size() != split_num))
		{
			imsi.push_back(arr[num][split_num]);
			arr[num].erase(arr[num].begin() + split_num);
			
		}
		arr.push_back(imsi);

		mysn.left = num + 1; mysn.right = arr.size();

		imsi.clear();
		return mysn;
	}

};

int main(int argc, char* argv[])
{

	BTree mytree;

	char command = argv[1][0];

	string in_file_name = "btree.bin";
	string out_file_name;
	string s;
	int num;

	switch (command)
	{

	case 'c': {
		out_file_name = argv[2];
		mytree.size = atoi(argv[3]);
		mytree.block_size = ((mytree.size - 4) / 8) * 2 + 1;

		ofstream out(out_file_name, std::ios::binary);

		out.write((char*)&mytree.size, 4);
		out.write((char*)&mytree.rootBID, 4);
		out.write((char*)&mytree.depth, 4);
		out.write((char*)&mytree.block_size, 4);
		out.write((char*)&mytree.block_num, 4);
		out.close();

		break;
	}
		
	case 'i':{

		ifstream in(in_file_name, std::ios::binary);
		in.read((char*)(&mytree.size), sizeof(num));
		in.read((char*)(&mytree.rootBID), sizeof(num));
		in.read((char*)(&mytree.depth), sizeof(num));
		in.read((char*)(&mytree.block_size), sizeof(num));
		in.read((char*)(&mytree.block_num), sizeof(num));

		if (mytree.rootBID == 0) mytree.rootBID++;

		for (int i = 0; i < mytree.block_num; i++) {
			vector<int>imsi;
			for (int j = 0; j < mytree.block_size; j++) {
				in.read((char*)(&num), sizeof(num));
				if (num != 0) imsi.push_back(num);
			}
			mytree.arr.push_back(imsi);
			imsi.clear();
		}

		in.close();


		in_file_name = argv[3];


		string in_line;
		in.open(in_file_name);
		while (getline(in, in_line)) {

			string* str = strSplit(in_line, ",");

			int key = atoi(str[0].c_str());
			int value = atoi(str[1].c_str());


			if (mytree.arr.empty()) {
				vector<int>imsi;
				imsi.push_back(key);
				imsi.push_back(value);
				mytree.arr.push_back(imsi);
				mytree.block_num++;
			}

			else if (mytree.depth == 0 && (mytree.arr.empty() || mytree.arr[0].size() < mytree.block_size)) //root만 있는경우
			{
				int i;
				bool pandan = true;
				for (i = 0; i < mytree.arr[0].size(); i += 2) {
					if (mytree.arr[0][i] > key) {
						mytree.arr[0].insert(mytree.arr[0].begin() + i, value);
						mytree.arr[0].insert(mytree.arr[0].begin() + i, key);
						pandan = false;
						break;
					}
				}
				if (pandan) {
					mytree.arr[0].push_back(key);
					mytree.arr[0].push_back(value);
				}
				if (mytree.arr[0].size() > mytree.block_size) //root는 leaf인 경우 첫 split
				{
					vector<int>imsi;
					int split_num = ((mytree.arr[0].size() + 2) / 4) * 2;
					while (mytree.arr[0].size() != split_num) {
						imsi.push_back(mytree.arr[0][split_num]);
						mytree.arr[0].erase(mytree.arr[0].begin() + split_num);
					}
					mytree.arr.push_back(imsi);
					mytree.arr[0].push_back(2);
					imsi.clear();
					imsi.push_back(1); imsi.push_back(mytree.arr[1][0]); imsi.push_back(2);
					mytree.arr.push_back(imsi);
					mytree.depth++;
					mytree.rootBID = 3;
				}

			}
			else //높이가 1이상으로 root가 leaf노드가 아닌경우
			{
				stack<pair<int, int>>history;

				int parent = mytree.rootBID;

				for (int i = 0; i < mytree.depth; i++) {
					int j = 1;
					while (1) {
						if (mytree.arr[parent - 1][j] > key) {
							history.push(make_pair(parent, j));
							parent = mytree.arr[parent - 1][j - 1];
							break;
						}
						j += 2;

						if (j >= mytree.arr[parent - 1].size()) {
							history.push(make_pair(parent, j - 2));
							parent = mytree.arr[parent - 1][j - 1];
							break;
						}

					}
				}

				int i;
				bool pandan = true;
				for (i = 0; i < mytree.arr[parent - 1].size(); i += 2) // leaf노드에서 삽입하는 과정
				{
					if (mytree.arr[parent - 1][i] > key) {
						mytree.arr[parent - 1].insert(mytree.arr[parent - 1].begin() + i, value);
						mytree.arr[parent - 1].insert(mytree.arr[parent - 1].begin() + i, key);
						pandan = false;
						break;
					}
				}
				if (pandan) {
					if (mytree.arr[parent - 1].size() % 2 == 0) {
						mytree.arr[parent - 1].push_back(key);
						mytree.arr[parent - 1].push_back(value);
					}
					else {
						int a = mytree.arr[parent - 1][mytree.arr[parent - 1].size() - 1];
						mytree.arr[parent - 1].erase(mytree.arr[parent - 1].begin() + (mytree.arr[parent - 1].size() - 1));
						mytree.arr[parent - 1].push_back(key);
						mytree.arr[parent - 1].push_back(value);
						mytree.arr[parent - 1].push_back(a);
					}

				}

				//split이 일어나야하나 check
				if (mytree.arr[parent - 1].size() > mytree.block_size)//leaf노드에서 split이 일어나야 하는 경우
				{
					//1. leaf노드에서의 split
					split_node mysn = mytree.leaf_split(parent - 1);
					while (!history.empty()) {
						pair<int, int>par_pair = history.top();
						history.pop();
						if (mytree.arr[par_pair.first - 1][par_pair.second] < mysn.key) {
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second + 1), mysn.right);
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second + 1), mysn.key);
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second + 1), mysn.left);
							mytree.arr[par_pair.first - 1].erase(mytree.arr[par_pair.first - 1].begin() + (par_pair.second + 4));
						}
						else {
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second), mysn.right);
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second), mysn.key);
							mytree.arr[par_pair.first - 1].insert(mytree.arr[par_pair.first - 1].begin() + (par_pair.second), mysn.left);
							mytree.arr[par_pair.first - 1].erase(mytree.arr[par_pair.first - 1].begin() + (par_pair.second - 1));
						}

						if (mytree.arr[par_pair.first - 1].size() > mytree.block_size) {
							//non_leaf split;
							mysn = mytree.non_leaf_split(par_pair.first - 1);


							if (history.empty()) {

								vector<int>imsi;
								imsi.push_back(mysn.left); imsi.push_back(mysn.key); imsi.push_back(mysn.right);
								mytree.arr.push_back(imsi);

								mytree.depth++;
								mytree.rootBID = mytree.arr.size();
							}
						}
						else break;
					}
					//2.
				}
			}
			//key와 value를 통해 삽입;
		}

		mytree.block_num = mytree.arr.size();


		out_file_name = argv[2];

		ofstream out(out_file_name, std::ios::binary);

		out.write((char*)&mytree.size, 4);
		out.write((char*)&mytree.rootBID, 4);
		out.write((char*)&mytree.depth, 4);
		out.write((char*)&mytree.block_size, 4);
		out.write((char*)&mytree.block_num, 4);

		for (int i = 0; i < mytree.block_num; i++) {
			for (int j = 0; j < mytree.arr[i].size(); j++) {
				out.write((char*)&mytree.arr[i][j], 4);
			}
			for (int j = 0; j < (mytree.block_size - mytree.arr[i].size()); j++) {
				out.write((char*)&zero, 4);
			}
		}

		out.close();
		in.close();
		break;

	}
	case 's': {

		string file_name = argv[2];

		in_file_name = argv[2];

		ifstream in(in_file_name, std::ios::binary);
		in.read((char*)(&mytree.size), sizeof(num));
		in.read((char*)(&mytree.rootBID), sizeof(num));
		in.read((char*)(&mytree.depth), sizeof(num));
		in.read((char*)(&mytree.block_size), sizeof(num));
		in.read((char*)(&mytree.block_num), sizeof(num));

		mytree.arr = vector<vector<int>>(mytree.block_num);

		in.close();
		//탐색연산 시작

		in_file_name = argv[3];
		out_file_name = argv[4];

		ofstream out(out_file_name);

		string in_line;
		in.open(in_file_name);
		while (getline(in, in_line)) {
			
			num = atoi(in_line.c_str());


			int parent = mytree.rootBID;
			for (int i = 0; i < mytree.depth; i++) {

				if (mytree.arr[parent - 1].empty()) {
					mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size*4, file_name);
				}
				int j = 1;
				while (1) {

					if (mytree.arr[parent - 1][j] > num) {
						parent = mytree.arr[parent - 1][j - 1];
						break;
					}
					j += 2;

					if (j >= mytree.arr[parent - 1].size()) {
						parent = mytree.arr[parent - 1][j - 1];
						break;
					}
					
				}
			}

			if (mytree.arr[parent - 1].empty()) {
				mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size*4, file_name);
			}

			for (int i = 0; i < mytree.arr[parent-1].size(); i += 2) {
				if (mytree.arr[parent-1][i] == num) {
					out << num << ',' << mytree.arr[parent-1][i + 1] << endl;
					break;
				}
			}

			for (int i = 0; i < mytree.arr.size(); i++) {
				mytree.arr[i].clear();
			}
		}

		out.close();
		in.close();
		// search keys in [input file] and print results to [output file]
		break;

	}
	case 'r': {

		string file_name = argv[2];

		in_file_name = argv[2];

		ifstream in(in_file_name, std::ios::binary);
		in.read((char*)(&mytree.size), sizeof(num));
		in.read((char*)(&mytree.rootBID), sizeof(num));
		in.read((char*)(&mytree.depth), sizeof(num));
		in.read((char*)(&mytree.block_size), sizeof(num));
		in.read((char*)(&mytree.block_num), sizeof(num));

		mytree.arr = vector<vector<int>>(mytree.block_num);

		in.close();
		//탐색연산 시작

		in_file_name = argv[3];
		out_file_name = argv[4];

		ofstream out(out_file_name);

		string in_line;
		in.open(in_file_name);
		while (getline(in, in_line)) {
			
			string* str = strSplit(in_line, ",");

			num = atoi(str[0].c_str());
			int end = atoi(str[1].c_str()); // num이 start, end가 마지막

			int parent = mytree.rootBID;
			for (int i = 0; i < mytree.depth; i++) {
				if (mytree.arr[parent - 1].empty()) {
					mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size*4, file_name);
				}

				int j = 1; 
				while (1) {
					if (mytree.arr[parent - 1][j] > num) {
						parent = mytree.arr[parent - 1][j - 1];
						break;
					}
					j += 2;

					if (j >= mytree.arr[parent - 1].size()) {
						parent = mytree.arr[parent - 1][j - 1];
						break;
					}

				}
			}

			if (mytree.arr[parent - 1].empty()) {
				mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size * 4, file_name);
			}

			for (int i = 0; i < mytree.arr[parent-1].size(); i += 2) {


				if (mytree.arr[parent-1][i] >= num && mytree.arr[parent-1][i]<=end) {
					out << mytree.arr[parent - 1][i] << ',' << mytree.arr[parent - 1][i + 1] << "  ";
				}
				else if (mytree.arr[parent - 1][i] > end) {
					break;
				}

				if (mytree.arr[parent - 1].size()%2 == 1 && i == mytree.arr[parent-1].size() - 3) {
					parent = mytree.arr[parent-1][i + 2];
					if (mytree.arr[parent - 1].empty()) {
						mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size * 4, file_name);
					}
					i = -2;
				}
			}
			out << endl;
			for (int i = 0; i < mytree.arr.size(); i++) {
				mytree.arr[i].clear();
			}
		}

		out.close();
		in.close();

		break;
	}
	case 'p': {
		string file_name = argv[2];

		in_file_name = argv[2];

		ifstream in(in_file_name, std::ios::binary);
		in.read((char*)(&mytree.size), sizeof(num));
		in.read((char*)(&mytree.rootBID), sizeof(num));
		in.read((char*)(&mytree.depth), sizeof(num));
		in.read((char*)(&mytree.block_size), sizeof(num));
		in.read((char*)(&mytree.block_num), sizeof(num));

		mytree.arr = vector<vector<int>>(mytree.block_num);

		//탐색연산 시작

		out_file_name = argv[3];

		ofstream out(out_file_name);

		out << endl << "<0>" << endl;
		int parent;

		if (mytree.depth == 0) {
			if (mytree.arr[0].empty()) {
				mytree.arr[0] = read_block(0, mytree.block_size * 4, file_name);
			}

			for (int i = 0; i < mytree.arr[0].size(); i += 2) {
				out << mytree.arr[0][i] << ", ";
			}

			out << endl << "<1>" << endl;
		}
		else if (mytree.depth == 1) {

			if (mytree.arr[mytree.rootBID - 1].empty()) {
				mytree.arr[mytree.rootBID - 1] = read_block(mytree.rootBID - 1, mytree.block_size * 4, file_name);
			}
			for (int i = 1; i < mytree.arr[mytree.rootBID - 1].size(); i += 2) {
				out << mytree.arr[mytree.rootBID - 1][i] << ", ";
			}

			out << endl << "<1>" << endl;

			for (int i = 0; i < mytree.arr[mytree.rootBID - 1].size(); i += 2) {
				parent = mytree.arr[mytree.rootBID - 1][i];
				if (mytree.arr[parent - 1].empty()) {
					mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size * 4, file_name);
				}
				for (int j = 0; j < mytree.arr[parent - 1].size(); j += 2) {

					out << mytree.arr[parent - 1][j] << ", ";
				}
			}
			out << endl;
		}
		else {
			if (mytree.arr[mytree.rootBID - 1].empty()) {
				mytree.arr[mytree.rootBID - 1] = read_block(mytree.rootBID - 1, mytree.block_size * 4, file_name);
			}
			for (int i = 1; i < mytree.arr[mytree.rootBID - 1].size(); i += 2) {
				out << mytree.arr[mytree.rootBID - 1][i] << ", ";
			}

			out << endl << "<1>" << endl;

			for (int i = 0; i < mytree.arr[mytree.rootBID - 1].size(); i += 2) {
				parent = mytree.arr[mytree.rootBID - 1][i];
				if (mytree.arr[parent - 1].empty()) {
					mytree.arr[parent - 1] = read_block(parent - 1, mytree.block_size * 4, file_name);
				}
				for (int j = 1; j < mytree.arr[parent - 1].size(); j += 2) {

					out << mytree.arr[parent - 1][j] << ", ";
				}
			}
			out << endl;
		}

		out.close();
		in.close();

		break;
	}
	default:
		break;
	}	return 0;}