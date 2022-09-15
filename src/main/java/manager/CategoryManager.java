package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        String sql = "INSERT INTO category(`name`)VALUES(?) ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAll() {
        String sql = "SELECT * FROM category";
        List<Category> categoryList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getById(int id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeCategoryById(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Category category) {
        String sql = "UPDATE category SET `name`=?,surname=?,email=?,age=?,profile_pic=? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
