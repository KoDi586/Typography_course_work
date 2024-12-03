package com.example.Typography_course_work.service;

import com.example.Typography_course_work.dto.OrderDTO.create.ClientRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.OrderItemRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.get.AllOrderResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.ClientResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderItemResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderResponseDto;
import com.example.Typography_course_work.dto.materialDto.get.MaterialForGetAllResponseDto;
import com.example.Typography_course_work.dto.materialDto.get.MaterialTwoFieldResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.AllProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.ProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.post.ProductRequestDto;
import com.example.Typography_course_work.dto.provider.ProviderAllResponseDto;
import com.example.Typography_course_work.dto.provider.ProviderResponseDto;
import com.example.Typography_course_work.model.*;
import com.example.Typography_course_work.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypographyService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final MaterialRepository materialRepository;
    private final ProviderRepository providerRepository;

    public void create(CreateOrderRequestDto createOrderRequestDto) {
        log.info(createOrderRequestDto.toString());
        try {
            convertOrderDtoToOrderModel(createOrderRequestDto);
        } catch (Exception e) {
            log.info("ошибка при сохранении заказа");
        }
    }

    private Order convertOrderDtoToOrderModel(CreateOrderRequestDto createOrderRequestDto) {
        Order orderModel = new Order();
        try {
            orderModel = orderRepository.save(orderModel);
        } catch (Exception e) {
            log.warn("error save in convertOrderDtoToOrderModel");
        }
        Long clientId = convertClientDtoToClientModel(createOrderRequestDto.getClient()).getId();
        if (orderModel.getId() == null) {
            log.warn("orderModel without id");
//            throw new RuntimeException();
        }
        List<Integer> orderItemListIds = convertOrderItemsDtoToOrderItemModel(createOrderRequestDto.getOrderItems(), orderModel.getId());
        orderModel.setClientId(clientId);
//        for (OrderItem orderItem : orderItemListIds) {
//
//        }
        orderModel.setOrderItems(orderItemListIds);
//        orderModel.setOrderItems(orderItemList1);
        Order orderSave = null;
        try {
            orderSave = orderRepository.save(orderModel);
        } catch (Exception e) {
            log.warn("error in convertOrderDtoToOrderModel repository save");
        }
        return orderSave;
//        orderRepository.saveByParams(orderModel.getId(), orderModel.getClientId(), orderModel.getOrderItems());
//        return orderRepository.findById()
//        return orderModel;
    }

    private List<Integer> convertOrderItemsDtoToOrderItemModel(List<OrderItemRequestDto> orderItems, Long orderId) {
        List<Integer> result = new ArrayList<>();
        for (OrderItemRequestDto orderItemRequestDto : orderItems) {
            result.add(convertOrderItemDtoToOrderItemModel(orderItemRequestDto, orderId).getId().intValue());
        }
        return result;
    }

    private OrderItem convertOrderItemDtoToOrderItemModel(OrderItemRequestDto dto, Long orderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(dto.getCount());
        Long productId = null;
        try {
            productId = productRepository.findById(Long.parseLong(dto.getProduct())).get().getId();
        } catch (NumberFormatException e) {
            log.warn("error in convertOrderItemDtoToOrderItemModel repository findById");
        }
        orderItem.setProductId(productId);
        orderItem.setOrderId(orderId);
        OrderItem save = null;
        try {
            save = orderItemRepository.save(orderItem);
        } catch (Exception e) {
            log.warn("error in convertOrderItemDtoToOrderItemModel repository save");
        }
        return save;
//        return orderItem;
    }

    private Client convertClientDtoToClientModel(ClientRequestDto dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setSecondName(dto.getSecondName());
        client.setCard(dto.getCard());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        Client save = null;
        try {
            save = clientRepository.save(client);
        } catch (Exception e) {
            log.warn("error in convertClientDtoToClientModel repository save");
        }
        return save;
        //        clientRepository.save(client);
//        return new Client(
//                0L,
//                clientRequestDto.getName(),
//                clientRequestDto.getSecondName(),
//                clientRequestDto.getEmail(),
//                clientRequestDto.getPhone(),
//                clientRequestDto.getCard()
//        );
    }

    public AllProductResponseDto getAllProducts() {
        List<Product> allProduct = null;
        try {
            allProduct = productRepository.findAll();
        } catch (Exception e) {
            log.warn("error in getAllProducts because repository find all");
        }
        List<ProductResponseDto> children = convertProductListToProductListDto(allProduct);
        return new AllProductResponseDto(children);
    }

    private List<ProductResponseDto> convertProductListToProductListDto(List<Product> allProduct) {
        List<ProductResponseDto> children = new ArrayList<>();
        for (Product product : allProduct) {
            children.add(
                    convertProductModelToProductResponseDto(product)
            );
        }
        return children;
    }

    private ProductResponseDto convertProductModelToProductResponseDto(Product product) {
        String materials = null;
        try {
            materials = convertMaterialsIdsToStringOfMaterials(product.getMaterials());
        } catch (Exception e) {
            log.warn("error in convertMaterialsIdsToStringOfMaterials");
        }
        return new ProductResponseDto(
                product.getId().toString(),
                product.getTitle(),
                product.getPrice(),
                materials
        );
    }

    private String convertMaterialsIdsToStringOfMaterials(List<Integer> materials) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer materialId : materials) {
            try {
                String title = materialRepository.findById(materialId.longValue()).get().getTitle();
                stringBuilder.append(title).append(", ");
            } catch (Exception e) {
                log.warn("error in materialRepository.findById");
            }
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public AllOrderResponseDto getAllOrders() {
//        int totalPrice = 0;
//        List<OrderResponseDto> children = new ArrayList<>();
        List<Order> orderList = null;
        try {
            orderList = orderRepository.findAll();
        } catch (Exception e) {
            log.warn("error in getAllOrders repository find all");
        }
        Collections.reverse(orderList);

        AllOrderResponseDto allOrderResponseDto = new AllOrderResponseDto();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            int totalPrice = 0;
            Client client = clientRepository.findById(order.getClientId()).get();
//            log.info("client is {}", client.toString());
            List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
            List<Integer> orderItemsIds = order.getOrderItems();
            for (Integer orderItemId : orderItemsIds) {
//                log.info("orderItemId is {}", orderItemId);
                OrderItem orderItem = orderItemRepository.findById(orderItemId.longValue()).get();
//                log.info("orderItem is {}", orderItem);
                Product product = productRepository.findById(orderItem.getProductId()).get();
//                log.info("product is {}", product);
                Integer count = orderItem.getCount();
                orderItemResponseDtoList.add(
                        new OrderItemResponseDto(product.getTitle(),
                                count)
                );
                totalPrice += product.getPrice() * count;
            }
            orderResponseDtoList.add(new OrderResponseDto(
                    order.getId(),
                    convertClientModelToClientDto(client),
                    orderItemResponseDtoList,
                    totalPrice

            ));


        }
        allOrderResponseDto.setChildren(orderResponseDtoList);


//        for (Order order : orderList) {
//            List<Long> orderItemsIds = order.getOrderItems();
//            List<OrderItemResponseDto> orderItemResponseDtos = new ArrayList<>();
//            for (Long orderItemId : orderItemsIds) {
//                orderItemResponseDtos.add(
//                        orderRepository.findById(orderItemId)
//
//                        new OrderItemResponseDto(
//                        orderItemId.getProduct().getTitle(),
//                        orderItemId.getCount()
//                ));
//                totalPrice += orderItemId.getProduct().getPrice() * orderItemId.getCount();
//
//            }
//            Client client = order.getClient();
//            children.add(new OrderResponseDto(
//                    new ClientResponseDto(client.getName(),
//                            client.getSecondName(),
//                            client.getEmail(),
//                            client.getPhone(),
//                            client.getCard()),
//                    orderItemResponseDtos,
//                    totalPrice
//            ));
//        }
//        return new AllOrderResponseDto(children);
        return allOrderResponseDto;
    }


    private ClientResponseDto convertClientModelToClientDto(Client client) {
        return new ClientResponseDto(
                client.getName(),
                client.getSecondName(),
                client.getEmail(),
                client.getPhone(),
                client.getCard()
        );
    }

    public void saveProduct(ProductRequestDto productDto) {
        try {
            productRepository.save(convertProductDtoToProductModel(productDto));
        } catch (Exception e) {
            log.warn("error in saveProduct because repository save");
        }
    }

    private Product convertProductDtoToProductModel(ProductRequestDto productDto) {

        Product product;
        try {
            product = productRepository.findById(productDto.getId()).get();
        } catch (Exception e) {
            product = new Product();
        }
        product.setPrice(productDto.getPrice_with_materials());
        product.setTitle(productDto.getTitle());
        List<Integer> materialsId = getMaterialsIdsByMaterialsNameList(productDto.getMaterials());
        product.setMaterials(materialsId);
        log.info("method convertProductDtoToProductModel product = {}", product.toString());
        return product;
    }

    private List<Integer> getMaterialsIdsByMaterialsNameList(List<String> materials) {
        List<Integer> result = new ArrayList<>();

        for (String materialName : materials) {

            try {
                result.add(materialRepository.findByTitle(materialName).getId().intValue());
            } catch (Exception e) {
                log.warn("error in getMaterialsIdsByMaterialsNameList because findByTitle");
            }

        }
        return result;
    }

    public ProductResponseDto getProductByProductId(Long productId) {
        Product productModel;
        try {
             productModel = productRepository.findById(productId).get();
        } catch (Exception e) {
            log.warn("error in getProductByProductId repository findById id = {}", productId);
            return null;
        }

        return convertProductModelToProductResponseDto(productModel);
    }

    public MaterialForGetAllResponseDto getAllMaterialsDtos() {

        List<Material> materialModelList = null;
        try {
            materialModelList = materialRepository.findAll();
        } catch (Exception e) {
            materialModelList = new ArrayList<>();
        }
        List<MaterialTwoFieldResponseDto> children = convertMaterialModelListToMaterialDtoList(materialModelList);
        return new MaterialForGetAllResponseDto(children);
    }

    private List<MaterialTwoFieldResponseDto> convertMaterialModelListToMaterialDtoList(List<Material> materialModelList) {
        List<MaterialTwoFieldResponseDto> result = new ArrayList<>();
        for (Material material : materialModelList) {
            result.add(convertMaterialToMaterialDto(material));
        }
        return result;
    }

    private MaterialTwoFieldResponseDto convertMaterialToMaterialDto(Material material) {
        return new MaterialTwoFieldResponseDto(
                material.getTitle(),
                material.getPrice()
        );
    }

    public ProviderAllResponseDto getAllProviders() {
        List<Provider> allProviders = null;
        try {
            allProviders = providerRepository.findAll();
        } catch (Exception e) {
            log.warn("error in find all provider");
        }
        List<ProviderResponseDto> children = convertAllProvidersToAllProviderDto(allProviders);
        return new ProviderAllResponseDto(children);
    }

    private List<ProviderResponseDto> convertAllProvidersToAllProviderDto(List<Provider> allProviders) {
        List<ProviderResponseDto> listDtos = new ArrayList<>();
        for (Provider provider : allProviders) {
            listDtos.add(convertProviderDtoProviderDto(provider));
        }
        return listDtos;
    }

    private ProviderResponseDto convertProviderDtoProviderDto(Provider provider) {
        Material material = null;
        try {
            material = materialRepository.findById(provider.getMaterialId()).get();
        } catch (Exception e) {
            log.warn("error in findById material");
        }
        String materialName;
        if (material == null) {
            materialName = " ";
        } else {
            materialName = material.getTitle();
        }
        return new ProviderResponseDto(
                provider.getId(),
                provider.getName(),
                materialName,
                provider.getContactInfo()
        );
    }
}
