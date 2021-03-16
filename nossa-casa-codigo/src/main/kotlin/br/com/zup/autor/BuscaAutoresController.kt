package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(@QueryValue(defaultValue = "")email: String): HttpResponse <List<DetalhesAutorResponse>> {
        val autores = autorRepository.findAll()
        val resposta = autores.map { autor -> DetalhesAutorResponse(autor) }
        return HttpResponse.ok(resposta)
    }




}