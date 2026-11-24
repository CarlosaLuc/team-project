package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import entity.Pet;
import use_case.add_pet.AddPetDataAccessInterface;
import use_case.remove_pet.RemovePetDataAccessInterface;

/**
 * Stores pets in a CSV file. Pets are loaded when the program starts and
 * the file is rewritten every time a pet is added or removed.
 * Used by both the Add Pet and the Remove Pet use cases, so they share the same pets.
 */
public class FilePetDataAccessObject implements AddPetDataAccessInterface, RemovePetDataAccessInterface {
    private static final String HEADER = "petId,name,breed,gender,vaccinated,age,weight,imagePath";

    private final Path csvPath;
    private final Map<String, Pet> pets = new LinkedHashMap<>();

    public FilePetDataAccessObject(String csvPath) {
        this.csvPath = Path.of(csvPath);
        if (Files.exists(this.csvPath)) {
            load();
        }
        else {
            writeAll();
        }
    }

    @Override
    public boolean existsById(String petId) {
        return pets.containsKey(petId);
    }

    @Override
    public void save(Pet pet) {
        pets.put(pet.getPetId(), pet);
        writeAll();
    }

    @Override
    public boolean removeById(String petId) {
        if (pets.remove(petId) == null) {
            return false;
        }
        writeAll();
        return true;
    }

    /** Returns the pet with this id, or null. */
    public Pet get(String petId) {
        return pets.get(petId);
    }

    /** Returns all pets in the order they were added. */
    public List<Pet> getAll() {
        return new ArrayList<>(pets.values());
    }

    private void load() {
        try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            if (line != null && !HEADER.equals(line)) {
                throw new IllegalStateException("Unexpected header in " + csvPath + ": " + line);
            }
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                final List<String> v = CsvUtils.split(line);
                final Pet pet = new Pet(v.get(0), v.get(1), v.get(2), v.get(3),
                        Boolean.parseBoolean(v.get(4)), Integer.parseInt(v.get(5)),
                        Double.parseDouble(v.get(6)), v.get(7));
                pets.put(pet.getPetId(), pet);
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException("Could not read " + csvPath, e);
        }
    }

    private void writeAll() {
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
            writer.write(HEADER);
            writer.newLine();
            for (Pet p : pets.values()) {
                writer.write(CsvUtils.join(p.getPetId(), p.getName(), p.getBreed(), p.getGender(),
                        String.valueOf(p.isVaccinated()), String.valueOf(p.getAge()),
                        String.valueOf(p.getWeight()), p.getImagePath()));
                writer.newLine();
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException("Could not write " + csvPath, e);
        }
    }
}
