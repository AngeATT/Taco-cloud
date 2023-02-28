package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.converters.StringToIngredientConverter;
import angeATT.Tacocloud.domains.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class JdbcIngredientRepository implements IngredientRepository{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }
    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id2,id, name, type from Ingredient",this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results =  jdbcTemplate.query("select id2,id, name, type from Ingredient where id=?",this::mapRowToIngredient,id);
        if (results.size() > 0) return Optional.of(results.get(0));
        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id2, id, name, type) values (?, ?, ?)",
                ingredient.getId2(),
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet,int row) throws SQLException {
        return new Ingredient(
                resultSet.getString("id2"),
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}