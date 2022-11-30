#include "TableModel.h"

TableModel::TableModel(SourceRepo& repo, QObject* parent) : sr{ repo }, QAbstractTableModel{ parent }
{
}

int TableModel::rowCount(const QModelIndex& parent) const
{
	return this->sr.getSourceFiles().size();
}

int TableModel::columnCount(const QModelIndex& parent) const
{
	return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();
	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		std::vector<SourceFile> files = this->sr.getSourceFiles();
		SourceFile sf = files[row];
		switch (column)
		{
		case 0:
			return QString::fromStdString(sf.getName());
		case 1:
			return QString::fromStdString(sf.getStatus());
		case 2:
			return QString::fromStdString(sf.getCreator());
		case 3:
			return QString::fromStdString(sf.getReviewer());
		default:
			break;
		}
	}
	return QVariant{};
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section)
			{
			case 0:
				return QString{ "Name" };
			case 1:
				return QString{ "Status" };
			case 2:
				return QString{ "Creator" };
			case 3:
				return QString{ "Reviewer" };
			default:
				break;
			}
		}
	}
	return QVariant{};
}

Qt::ItemFlags TableModel::flags(const QModelIndex& index) const
{
	return Qt::ItemIsEditable | Qt::ItemIsSelectable | Qt::ItemIsEnabled;
}

void TableModel::updateInternalData()
{
	endResetModel();
}

TableModel::~TableModel()
{
}
