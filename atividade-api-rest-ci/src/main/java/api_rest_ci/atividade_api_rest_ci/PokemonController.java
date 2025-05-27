package api_rest_ci.atividade_api_rest_ci;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) throws Exception {

        try {
            var getPokemonById = pokemonService.getPokemonById(id);
            return ResponseEntity.ok().body(getPokemonById);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> postPokemon(@RequestBody Pokemon pokemon) {

        try {
            var postPokemon = pokemonService.postPokemon(pokemon);
            return ResponseEntity.ok().body(postPokemon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
