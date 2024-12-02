package com.example.Typography_course_work.service;

import com.example.Typography_course_work.dto.OrderDTO.create.ClientRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.create.OrderItemRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.get.AllOrderResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.ClientResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderItemResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.AllProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.ProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.post.ProductRequestDto;
import com.example.Typography_course_work.model.Client;
import com.example.Typography_course_work.model.Order;
import com.example.Typography_course_work.model.OrderItem;
import com.example.Typography_course_work.model.Product;
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
//        orderRepository.save(orderModel);
        Long clientId = convertClientDtoToClientModel(createOrderRequestDto.getClient()).getId();
        List<Integer> orderItemListIds = convertOrderItemsDtoToOrderItemModel(createOrderRequestDto.getOrderItems());
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

    private List<Integer> convertOrderItemsDtoToOrderItemModel(List<OrderItemRequestDto> orderItems) {
        List<Integer> result = new ArrayList<>();
        for (OrderItemRequestDto orderItemRequestDto : orderItems) {
            result.add(convertOrderItemDtoToOrderItemModel(orderItemRequestDto).getId().intValue());
        }
        return result;
    }

    private OrderItem convertOrderItemDtoToOrderItemModel(OrderItemRequestDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(dto.getCount());
        Long productId = null;
        try {
            productId = productRepository.findById(Long.parseLong(dto.getProduct())).get().getId();
        } catch (NumberFormatException e) {
            log.warn("error in convertOrderItemDtoToOrderItemModel repository findById");
        }
        orderItem.setProductId(productId);
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
                    new ProductResponseDto(
                            product.getId().toString(),
                            product.getTitle(),
                            product.getPrice()
                    )
            );
        }
        return children;
    }

    public AllOrderResponseDto getAllOrders() {
        int totalPrice = 0;
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
            Client client = clientRepository.findById(order.getClientId()).get();
            List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
            List<Integer> orderItemsIds = order.getOrderItems();
            for (Integer orderItemId : orderItemsIds) {
                OrderItem orderItem = orderItemRepository.findById(orderItemId.longValue()).get();
                Product product = productRepository.findById(orderItem.getProductId()).get();
                Integer count = orderItem.getCount();
                orderItemResponseDtoList.add(
                        new OrderItemResponseDto(product.getTitle(),
                                count)
                );
                totalPrice += product.getPrice() * count;
            }
            orderResponseDtoList.add(new OrderResponseDto(
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
}
