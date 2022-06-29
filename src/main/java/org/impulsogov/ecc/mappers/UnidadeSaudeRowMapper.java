package org.impulsogov.ecc.mappers;

import org.impulsogov.ecc.models.UnidadeSaude;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnidadeSaudeRowMapper implements RowMapper<UnidadeSaude> {

    @Override
    public UnidadeSaude mapRow(ResultSet rs, int rowNum) throws SQLException {
        UnidadeSaude unidadeSaude = new UnidadeSaude();
        unidadeSaude.setId(rs.getLong("co_seq_dim_unidade_saude"));
        unidadeSaude.setCnes(rs.getString("nu_cnes"));
        unidadeSaude.setNome(rs.getString("no_unidade_saude"));
        unidadeSaude.setNomeFiltro(rs.getString("ds_filtro"));
        unidadeSaude.setBairroNome(rs.getString("no_bairro"));
        unidadeSaude.setRegistroValido(rs.getInt("st_registro_valido"));

        return unidadeSaude;
    }
}
