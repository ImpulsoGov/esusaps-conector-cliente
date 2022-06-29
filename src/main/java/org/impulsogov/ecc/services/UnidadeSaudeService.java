package org.impulsogov.ecc.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.impulsogov.ecc.mappers.UnidadeSaudeRowMapper;
import org.impulsogov.ecc.models.UnidadeSaude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UnidadeSaudeService {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ONE = "select * from tb_dim_unidade_saude where st_registro_valido = 1 and st_enviado_impulsogov is false limit 1";

    private final String SQL_UPDATE_ENVIADO_IMPULSO =
            "update tb_dim_unidade_saude set st_enviado_impulsogov = ? where co_seq_dim_unidade_saude = ?";

    @Value("${impulsogov.conectorUrl}")
    private String conectorUrl;

    @Value("${impulsogov.codigoIBGE}")
    private String ibgeOrigem;

    public UnidadeSaudeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public UnidadeSaude getUnidadeSaudeNaoEnviada() {
        List<UnidadeSaude> items = jdbcTemplate.query(SQL_GET_ONE, new UnidadeSaudeRowMapper());
        if (items.isEmpty()) {
            return null;
        } else if (items.size() > 0) {
            return items.get(0);
        } else {
            return null;
        }
    }

    public boolean updateEnviadoImpulso(Long id) {
        return jdbcTemplate.update(SQL_UPDATE_ENVIADO_IMPULSO, true, id) > 0;
    }

    public void transmit(UnidadeSaude unidadeSaude) throws JSONException {
        JSONObject unidadeSaudeObject = new JSONObject();
        unidadeSaudeObject.put("idOrigem", unidadeSaude.getId());
        unidadeSaudeObject.put("ibgeOrigem", this.ibgeOrigem);
        unidadeSaudeObject.put("cnes", unidadeSaude.getCnes());
        unidadeSaudeObject.put("nome", unidadeSaude.getNome());
        unidadeSaudeObject.put("nomeFiltro", unidadeSaude.getNomeFiltro());
        unidadeSaudeObject.put("bairroNome", unidadeSaude.getBairroNome());
        unidadeSaudeObject.put("registroValido", unidadeSaude.getRegistroValido() == 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(unidadeSaudeObject.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = conectorUrl + "unidade-saude";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
    }
}
