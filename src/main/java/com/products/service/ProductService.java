package com.products.service;
import com.products.mapper.ProductMapper;
import com.products.model.Characteristic;
import com.products.model.Link;
import com.products.model.Product;
import com.products.repository.CharacteristicEntity;
import com.products.repository.CharacteristicRepository;
import com.products.repository.ProductEntity;
import com.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    ProductMapper mapper;
    public Product get(Long id){
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        Product pr = mapper.produtEntityToProdut(productEntity);
        Link l = new Link("product",id.toString());
        pr.setLink(l);
       return pr;
    }
    public List<Product> getAll() {
        var result =  productRepository.findAll()
                .stream()
                .map(productEntity -> mapper.produtEntityToProdut(productEntity)).toList();
                result.forEach(product ->
                        product.setLink(new Link("product",product.getId().toString())));
    return result;
    }
    private Product mapProduit(ProductEntity productEntity) {
        Product pr = new Product();
        pr.setId(productEntity.getId());
        pr.setNom(productEntity.getNom());
        pr.setType(productEntity.getType());
        pr.setCharacteristics(mapCharstwo(productEntity.getCharacteristics()));
        return pr;
    }
    public Product save(Product product) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setNom(product.getNom());
        productEntity.setType(product.getType());
       // Url url = new Url("product",productEntity.getId().toString());
       // productEntity.setUrl(url);
        List<CharacteristicEntity> characteristicsE = mapChars( product.getCharacteristics());
        productEntity.setCharacteristics(characteristicsE);
        characteristicRepository.saveAll(characteristicsE);
        ProductEntity savedPE = productRepository.save(productEntity);
        return mapper.produtEntityToProdut(savedPE);
    }
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "item deleted";
    }
    public Product update(Long id, Product prd) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        productEntity.setNom(prd.getNom());
        productEntity.setType(prd.getType());
       // List<CharacteristicEntity> characteristicsE = mapChars( prd.getCharacteristics());
       // characteristicRepository.deleteAll(productEntity.getCharacteristics());
        //
       // productEntity.setCharacteristics(new ArrayList<>());
       // productEntity.setCharacteristics(characteristicsE);
       // System.out.println(productEntity);
       // characteristicRepository.saveAll(characteristicsE);
        productRepository.save(productEntity);
       // ProductEntity savedPE = productRepository.save(productEntity);
        return mapper.produtEntityToProdut(productEntity);
    }
    public List<Characteristic> getProductCharacteristics(Long id) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        List<CharacteristicEntity> characteristicsE = productEntity.getCharacteristics();
        return mapCharstwo(characteristicsE);
    }
    public Characteristic productCharacteristic(Long productid, Long Characteristicid) {
      checkCharacteristicexistence(Characteristicid);
      Characteristic characteristic = charentitytochar(characteristicRepository.findById(Characteristicid).get());
        Link l = new Link("Characteristic",Characteristicid.toString());
        characteristic.setLink(l);
      return characteristic;
    }
    public Product addCharacteristic(Long id, List<Characteristic> characteristic) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        List<CharacteristicEntity> oldchar = productEntity.getCharacteristics();
        oldchar.addAll(mapChars(characteristic));
        productEntity.setCharacteristics(oldchar);
        characteristicRepository.saveAll(mapChars(characteristic));
        productRepository.save(productEntity);

        System.out.println(productEntity);
        return mapProduit(productEntity);
    }
    public String deletALLcharacteristic(Long id) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        List <CharacteristicEntity> temp = productEntity.getCharacteristics();
        productEntity.setCharacteristics(new ArrayList<>());
        temp.removeAll(temp);
        productRepository.save(productEntity);
        characteristicRepository.deleteAll(temp) ;
        return "ALL characteristics was deleted";
    }
    public String deletecharacteristic(Long id, Long id2) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        ProductEntity productEntity = produiteEntiteO.get();
        checkProductExistence(Optional.of(productEntity));
        checkCharacteristicexistence(id2);
        productRepository.save(productEntity);
        characteristicRepository.deleteById(id2);
        //System.out.println(productEntity);
        return "characteristic deleted";
    }
    public void checkProductExistence(Optional<ProductEntity> produiteEntiteO){
        if (produiteEntiteO.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the product is not found"
            );
        }}
    public void checkCharacteristicexistence(Long Characteristicid ) {
        if( !characteristicRepository.existsById(Characteristicid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the product is not found"
            );}
    }
    private List<CharacteristicEntity> mapChars(List<Characteristic> characteristics) {
        if (characteristics == null) return new ArrayList<>();

        return  characteristics.stream().map(this::mapChar).toList();
    }
    private CharacteristicEntity mapChar(Characteristic characteristic) {
        CharacteristicEntity d = new CharacteristicEntity();
        // d.setId(characteristic.getId());
        d.setName(characteristic.getName());
        d.setLabel(characteristic.getLabel());
        return d;
    }
    // next two fonction used to move from characteristicEnity to Characteristic ' mapCharstwo' and 'charentitytochar'
    private List<Characteristic> mapCharstwo(List<CharacteristicEntity> characteristicsEntity) {
        if (characteristicsEntity == null) return new ArrayList<>();

        return  characteristicsEntity.stream().map(this::charentitytochar).toList();
    }
    private Characteristic charentitytochar(CharacteristicEntity characteristic) {
        Characteristic d = new Characteristic();
        d.setId(characteristic.getId());
        d.setName(characteristic.getName());
        d.setLabel(characteristic.getLabel());
        return d;
    }
}