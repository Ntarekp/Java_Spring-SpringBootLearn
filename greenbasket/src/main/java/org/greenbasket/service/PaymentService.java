package org.greenbasket.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.greenbasket.entity.Order;
import org.greenbasket.entity.Payment;
import org.greenbasket.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    public Payment createPayment(Order order) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        int amountInCents = order.getItems().stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(100)).intValue();

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amountInCents);
        params.put("currency", "usd");
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(new BigDecimal(amountInCents).divide(new BigDecimal(100)));
        payment.setStripePaymentIntentId(paymentIntent.getId());
        paymentRepository.save(payment);
        return payment;
    }
}
