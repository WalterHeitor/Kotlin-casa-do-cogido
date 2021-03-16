package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

@Controller("/autores/")
class DeletaAutorController(val autorRepository: AutorRepository) {
    @Delete("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): HttpResponse<Any>{
        val possivelAutor = autorRepository.findById(id)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }
}