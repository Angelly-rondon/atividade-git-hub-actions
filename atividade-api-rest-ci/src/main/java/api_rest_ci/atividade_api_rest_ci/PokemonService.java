package api_rest_ci.atividade_api_rest_ci;

import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon getPokemonById(Long id) throws Exception {
        var pokemonById = pokemonRepository.findById(id);

        if (pokemonById.isPresent()){
            return pokemonById.get();
        } else {
            throw new Exception("Pokemon não encontrado! :(");
        }
    }

    public Pokemon postPokemon(Pokemon pokemon) throws Exception {
        try {
            var insertPokemon = pokemonRepository.save(pokemon);
            return insertPokemon;
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar seu pokemon! :(");
        }
    }

}
