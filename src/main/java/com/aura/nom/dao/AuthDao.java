package com.aura.nom.dao;

import com.aura.nom.config.DataSourceConfig;
import com.aura.nom.dto.AuthDto;
import com.aura.nom.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthDao {
    Logger LOGGER = LoggerFactory.getLogger(AuthDao.class);

    @Autowired
    private DataSourceConfig dataSourceConfig;

    public AuthDto getColaborador(String email) throws UsernameNotFoundException {

        DataSource dataSource = dataSourceConfig.getDataSource();
        String query = "call NOM035_VALIDATE_USER(?);";


        try(Connection con = dataSource.getConnection(); CallableStatement cStmt = con.prepareCall(query)) {
            cStmt.setString(1,email);
            cStmt.execute();

            ResultSet resultSet = cStmt.getResultSet();
            if(resultSet.next()){
                AuthDto authDto = new AuthDto();
                authDto.setIdCliente(resultSet.getInt("clnt_id"));
                authDto.setIdColaborador(resultSet.getInt("clave_colaborador"));
                authDto.setIdPerson(resultSet.getInt("pers_id"));
                authDto.setNombre(resultSet.getString("NOMBRE_COMPLETO"));
                authDto.setEmail(resultSet.getString("e_mail_registro"));
                authDto.setPassword(resultSet.getString("password"));
                return authDto;

            }else{
                throw new UsernameNotFoundException("Colaborador NOT FOUND");
            }

        }catch (SQLException e){
            LOGGER.error("Error obteniendo datos del colaborador: "+e.getMessage(),e);
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

}
