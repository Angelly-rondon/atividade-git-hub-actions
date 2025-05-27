package api_rest_ci.atividade_api_rest_ci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PokemonServiceTest {

    PokemonService pokemonService;
    PokemonRepository pokemonRepository;

    @BeforeEach
    void initialize() {
        pokemonRepository = mock(PokemonRepository.class);
        pokemonService = new PokemonService(pokemonRepository);
    }

    @Test
    void testGetPokemonById_whenPokemonExists() throws Exception {
        Long id = 1L;
        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);
        pokemon.setName("Pikachu");

        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemon));

        Pokemon result = pokemonService.getPokemonById(id);

        assertNotNull(result);
        assertEquals("Pikachu", result.getName());
    }

    @Test
    void testGetPokemonById_whenPokemonDoesNotExist() {
        Long id = 2L;
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> pokemonService.getPokemonById(id));
        assertEquals("Pokemon não encontrado! :(", exception.getMessage());
    }

    @Test
    void testPostPokemon_whenSaveSuccessful() throws Exception {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Charmander");

        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        Pokemon result = pokemonService.postPokemon(pokemon);

        assertNotNull(result);
        assertEquals("Charmander", result.getName());
    }

    @Test
    void testPostPokemon_whenSaveFails() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(2L);
        pokemon.setName("Bulbasaur");

        when(pokemonRepository.save(pokemon)).thenThrow(new RuntimeException("Erro no banco de dados"));

        Exception exception = assertThrows(Exception.class, () -> pokemonService.postPokemon(pokemon));
        assertEquals("Não foi possível salvar seu pokemon! :(", exception.getMessage());
    }
}
