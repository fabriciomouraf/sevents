# Getting Started


### Dicas
Algumas anotações de padrão que utilizei para construir a aplicação:

* Usar @Autowired diretamente nos campos é considerado
* uma prática menos ideal do que usar injeção de dependência
* por construtor porque torna sua classe mais difícil
* de testar sem um container Spring e pode tornar as 
* dependências menos explícitas.

* Exemplo:
```java
@RestController
@RequestMapping(ConstantsExternalResources.RESOURCE_EVENTO_ANCORA)
@AllArgsConstructor
@Slf4j
public class EventoAncoraController {

    private final EventoAncoraService eventoAncoraService;
```

### Zalando Status

* org.zalando.problem.Status é uma parte da biblioteca "Problem"
* , que é uma implementação em Java da especificação 
* "Problem Details for HTTP APIs" (RFC 7807). Essa especificação 
* define um formato "problem detail" para enviar informações de 
* erro em APIs HTTP. É uma forma padronizada de enviar detalhes de 
* erro em uma resposta HTTP para que o cliente possa entender e tratar 
* o erro de forma mais eficaz.

Exemplo de onde se usar o status:

```java
@SuppressWarnings( "rawtypes")
public class NotFoundAlertException extends RequestAlertException {

     private static final long serialVersionUID = 1L;

     public NotFoundAlertException(String defaultMessage, Class entidade, ErrorCodeEnum error){
          super(defaultMessage, Status.NOT_FOUND, entidade, error);
     }
}
```

### Sqls nas entidades
* @SQLDelete: Esta anotação modifica o comportamento padrão da 
* operação de exclusão. Em vez de deletar a linha do banco de dados, 
* o SQL especificado é executado. No seu caso, UPDATE evento_ancora SET
* deletado_em = current_timestamp WHERE id=? é executado quando a entidade
* EventoAncora é deletada, marcando-a como deletada logicamente ao definir 
* a coluna deletado_em com o timestamp atual, em vez de remover a linha do
* banco de dados.

* @Where: Esta anotação é usada para adicionar uma 
* cláusula WHERE à consulta SQL que é executada toda 
* vez que você busca EventoAncora do banco de dados. Com a
* cláusula deletado_em IS NULL, o Hibernate irá automaticamente 
* filtrar e retornar apenas as entidades que não foram marcadas 
* como deletadas (ou seja, aquelas onde a coluna deletado_em é NULL).

```java
@Entity
@Table(name = "evento_ancora")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE evento_ancora SET deletado_em = current_timestamp WHERE id=?")
@Where(clause = "deletado_em IS NULL")
public class EventoAncora extends GenericObject {
```

### Explicação do modelo de paginação

* Neste exemplo abaixo, está o padrão de como vai ser feita a ordenação, filtros, e paginação.
* Abaixo estou usando a entidade Evento Ancora, e estou setando o numero de pagina, limite(que será a quantidade que vai ser exibida), e sort.
* No sort, basta colocar o nome do atributo da classe Evento Ancora e se quer ordenar por asc ou desc, que irá fazer a ordenação.
* Caso queira Filtrar a listagem, basta passar o nome do atributo e o valor, para ser feito o filtro e o retorno certo.

### Exemplo do Codigo
* Foi utilizado o codigo abaixo com as classes utilitárias para evitar problemas com a paginação do spring
* Nas classes utilitárias há PageableBevent, que será responsável por executar todo o tipo de paginação da aplicação inteira,
* passando os valores, ela irá fazer a paginação de QUALQUER TIPO de classe.
* Veja que o tipo é EventoAncora.class, podendo ser Evento.class, Ingresso.class que irá realizar a paginação corretamente

#### Exemplo
```java
Page<EventoAncora> page = eventoAncoraRepository.findAll(Example.of(eventoAncora, exampleMatcher),
PageableBevent.setPageable(request.getPage(), request.getLimit(), EventoAncora.class, request.getSort()));
```

### Postman
```
https://storage.googleapis.com/dl.behoh.com/new_beevent.postman_collection
```




