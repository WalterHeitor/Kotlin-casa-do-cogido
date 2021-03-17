package br.com.zup.autor


import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data  class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email  val email: String, //@field:Duplicidade
    @field:NotBlank @field:Size(max= 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun paraAutor(endercoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(endercoResponse, numero)
        return Autor(nome, email, descricao, endereco)
    }

}
