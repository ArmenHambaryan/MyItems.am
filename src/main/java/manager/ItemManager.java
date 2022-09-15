package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    private final CategoryManager categoryManager = new CategoryManager();

    private final UserManager userManager = new UserManager();

    public void add(Item item) {
        String sql = "INSERT INTO item(title, price, pic_url, user_id, category_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getPicUrl());
            ps.setInt(4, item.getUser().getId());
            ps.setInt(5, item.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        String sql = "SELECT * FROM item ORDER BY id DESC LIMIT 20";
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getById(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeItemById(int itemId) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemByUserId(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getCategory(int categoryId) throws SQLException {
        String sql = "SELECT * FROM item WHERE category_id = ?";
        List<Item> items = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            items.add(getItemFromResultSet(resultSet));
        }
        return items;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt(1));
        item.setTitle(resultSet.getString(2));
        item.setPrice(resultSet.getDouble(3));
        item.setPicUrl(resultSet.getString(4));
        int userId = resultSet.getInt(5);
        int category_id = resultSet.getInt(6);
        userManager.getById(userId);
        categoryManager.getById(category_id);
        return item;
    }
}


