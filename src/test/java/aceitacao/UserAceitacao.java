package aceitacao;


import aceitacao.dto.UserDTO;
import aceitacao.dto.UserListDTO;
import aceitacao.dto.UserListIdDTO;
import aceitacao.service.UserService;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class UserAceitacao {
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    Random random = new Random();
    Faker faker = new Faker();
    UserService userService = new UserService();

    /* Endpoint: /users/create --------------------------------------------------------------------------------- */
    @Test
    public void create() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true)+"#");

        UserDTO res = userService.create(jsonObject.toString());

        //Validações
        Assert.assertFalse(res.getIdUser().isEmpty());
        Assert.assertTrue(Integer.parseInt(res.getIdUser()) > 0);
    }

    @Test
    public void createSemCargo() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true));

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createSemNome() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true));

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createSemEmail() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true));

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createSemEmailDBC() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@email.com.br");
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true)+"#");

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createEmailExiste() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        createUserDefault();

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", "testeLogin@email.com.br");
        jsonObject.put("userPassword", faker.internet().password(4, 16, true, true)+"#");

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createSemSenha() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");

        ValidatableResponse res = userService.createBadRequest(jsonObject.toString());

        //Validações
        res.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    /* Endpoint: /users/update-file --------------------------------------------------------------------------------- */
    @Test
    public void fileUpdate() throws IOException {
        UserDTO user = createUserFunction();

        ValidatableResponse res = userService.updateFile(user.getIdUser());

        //Validações
        res.statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void fileUpdateEmpty() throws IOException {
        UserDTO user = createUserFunction();

        ValidatableResponse res = userService.updateFileEmpty(user.getIdUser());

        //Validações
        res.statusCode(HttpStatus.SC_OK);
    }

    /* Endpoint: /users/login --------------------------------------------------------------------------------- */
    @Test
    public void userLogin() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/login.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        UserDTO user = createUserFunction();
        UserListIdDTO userInfo = listUser(user.getIdUser());

        //Definindo as values do json
        jsonObject.put("login", userInfo.getEmail());
        jsonObject.put("senha", "1234@a");

        Response res = userService.userLogin(jsonObject.toString());

        //Validações
        Assert.assertEquals(res.statusCode(),HttpStatus.SC_OK);

    }

    @Test
    public void userLoginEmailIncorreto() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/login.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("login", "incorreto@dbccompany.com.br");
        jsonObject.put("senha", "1234@a");

        Response res = userService.userLogin(jsonObject.toString());

        //Validações
        Assert.assertEquals(res.statusCode(),HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }

    @Test
    public void userLoginSenhaIncorreto() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/login.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        UserDTO user = createUserFunction();
        UserListIdDTO userInfo = listUser(user.getIdUser());

        //Definindo as values do json
        jsonObject.put("login", userInfo.getEmail());
        jsonObject.put("senha", "1234@b");

        Response res = userService.userLogin(jsonObject.toString());

        //Validações
        Assert.assertEquals(res.statusCode(),HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }

    /* Endpoint: /users/list-all --------------------------------------------------------------------------------- */
    @Test
    public void userListAll(){
        UserListDTO[] res = userService.listAll();

        //Validações
        Assert.assertTrue(res.length > 0);
    }

    /* Endpoint: /users/retornar-usuario --------------------------------------------------------------------------*/
    @Test
    public void userListById() throws IOException {
        UserDTO user = createUserFunction();
        UserListIdDTO res = userService.listUserById(user.getIdUser());

        //Validações
        Assert.assertEquals(res.getIdUser(),user.getIdUser());
    }

    /* Endpoint: /users/recuperar-usuario-logado --------------------------------------------------------------------------*/
    @Test
    public void listLoggedUser() throws IOException {
        ValidatableResponse res = userService.listLoggedUser();

        //Validações
        res.statusCode(HttpStatus.SC_OK);
    }

    /*UTILS--------------------------------------------------------------------------------------------------------*/
    public UserDTO createUserFunction() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");
        jsonObject.put("userPassword", "1234@a");

        UserDTO res = userService.create(jsonObject.toString());
        return res;
    }

    public UserDTO createUserDefault() throws IOException {
        //Array de cargos
        String[] arrayRoles = {"AGILE_COACH", "ANALISTA_DE_DADOS", "ANALISTA_DE_RH", "ANALISTA_DE_TESTES", "ANALISTA_DE_SUPORTE",
                "ARQUITETO_DE_SISTEMAS", "ASSISTENTE_COMERCIAL", "COORDENADOR_DE_DEPARTAMENTO_PESSOAL", "DESENVOLVEDOR_DE_SOFTWARE",
                "ENGENHEIRO_DE_DADOS", "ENGENHEIRO_DE_SOFTWARE", "GERENTE_DE_PROJETOS", "LIDER_TECNICO", "UX_DESIGNER", "GERENTE_DE_SOLUCOES"};

        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/user.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        //Definindo as values do json
        jsonObject.put("name", faker.name().fullName());
        int randomIndex = random.nextInt(arrayRoles.length);
        jsonObject.put("userRole", arrayRoles[randomIndex]);
        jsonObject.put("email", "testeLogin@dbccompany.com.br");
        jsonObject.put("userPassword", "1234@a");

        UserDTO res = userService.create(jsonObject.toString());
        return res;
    }

    public UserListIdDTO listUser(String id) throws IOException {
        UserListIdDTO res = userService.listUserById(id);
        return res;
    }

    public String authToken() throws IOException {
        //Criando um objeto json
        String jsonBody = lerJson("src/test/resources/login.json");
        JSONObject jsonObject = new JSONObject(jsonBody);

        UserDTO user = createUserFunction();
        UserListIdDTO userInfo = listUser(user.getIdUser());

        //Definindo as values do json
        jsonObject.put("login", userInfo.getEmail());
        jsonObject.put("senha", "1234@a");

        Response res = userService.userLogin(jsonObject.toString());

        String token = res.getBody().toString();

        return token;
    }
}
