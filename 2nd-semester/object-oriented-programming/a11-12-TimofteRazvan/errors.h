#pragma once
#include <exception>

class RepositoryException : public std::exception
{
	const char* file;
	int line;
	const char* func;
	const char* info;

public:
	RepositoryException(const char* msg, const char* _file, int _line, const char* _func, const char* _info = "") : std::exception{ msg },
		file{ _file }, line{ _line }, func{ _func }, info{ _info } {}

	const char* get_file() const { return file; }
	int get_line() const { return line; }
	const char* get_func() const { return func; }
	const char* get_info() const { return info; }
};