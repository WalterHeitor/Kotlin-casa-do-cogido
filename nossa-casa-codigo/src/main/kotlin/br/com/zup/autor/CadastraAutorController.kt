package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(
    @Inject val autorRepository: AutorRepository, //
    @Inject val enderecoClient: EnderecoClient  //
) {
    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        println("Requisicao $request")
        val endercoResponse: HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)
        if(endercoResponse.body() == null){
            return HttpResponse.badRequest()
        }
        val autor = request.paraAutor(endercoResponse.body())
        autorRepository.save(autor)
        println("Autor ${autor.nome}")
        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(DetalhesAutorResponse(autor), uri)
    }
}