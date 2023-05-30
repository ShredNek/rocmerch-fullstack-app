package com.rocmerchbackend.rocmerchbackend;

public class Utils {

    public String getItemsHtmlTemplate() {
        return itemsHtmlTemplate;
    }

    public void setItemsHtmlTemplate(String itemsHtmlTemplate) {
        this.itemsHtmlTemplate = itemsHtmlTemplate;
    }

    public String getOrderShippedHtmlTemplate() {
        return orderShippedHtmlTemplate;
    }

    public void setOrderShippedHtmlTemplate(String orderShippedHtmlTemplate) {
        this.orderShippedHtmlTemplate = orderShippedHtmlTemplate;
    }

    private String orderShippedHtmlTemplate = """
                <!DOCTYPE html>
                <html
                  lang="en"
                  style="--main-grey: #676060; --light-grey: #dbd6d6; --main-white: #ffffff"
                >
                  <head>
                    <meta charset="UTF-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <link
                      href="https://fonts.googleapis.com/css2?family=Raleway:wght@300;500;700&display=swap"
                      rel="stylesheet"
                    />
                    <title>Document</title>
                    <style>
                      *::before {
                        font-family: Raleway;
                        letter-spacing: 0.025em;
                        box-sizing: border-box;
                      }
                      *::after {
                        font-family: Raleway;
                        letter-spacing: 0.025em;
                        box-sizing: border-box;
                      }
                    </style>
                  </head>
                  <body
                    style="
                      font-family: Raleway;
                      letter-spacing: 0.025em;
                      box-sizing: border-box;
                      margin: 0;
                      min-width: 475px;
                      overflow: scroll;
                    "
                  >
                    <h1
                      class="email-header"
                      style="
                        font-family: Raleway;
                        letter-spacing: 0.025em;
                        box-sizing: border-box;
                        margin-top: 3rem;
                        width: 80%;
                        text-align: center;
                        margin-left: auto;
                        margin-right: auto;
                      "
                    >
                      Ripples Of Change - Official Merchandise Website
                    </h1>
                    <div
                      class="email-content"
                      style="
                        font-family: Raleway;
                        letter-spacing: 0.025em;
                        box-sizing: border-box;
                        margin-top: 3rem;
                        width: 80%;
                        margin: 3rem auto;
                        max-width: 800px;
                        border: 2px solid #676060;
                        border-radius: 5px;
                      "
                    >
                      <div
                        class="order-heading"
                        style="
                          font-family: Raleway;
                          letter-spacing: 0.025em;
                          box-sizing: border-box;
                          width: 100%;
                          padding: 2em;
                          background-color: #676060;
                          color: #ffffff;
                          text-align: center;
                        "
                      >
                        <h2
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                            font-weight: 500;
                          "
                        >
                          Thank you, {NAME_FOR_ORDER}.
                          <br
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                            "
                          />
                          your order is on the way!
                        </h2>
                      </div>
                      <div
                        class="order-subheadings"
                        style="
                          font-family: Raleway;
                          letter-spacing: 0.025em;
                          box-sizing: border-box;
                          width: 100%;
                        "
                      >
                        <p
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                            font-size: 0.75em;
                            padding: 1rem 2rem;
                            margin: auto;
                            margin-left: 0;
                            padding-top: 3rem;
                            font-weight: 300;
                            font-size: 1em;
                          "
                        >
                          We have finished processing your order
                        </p>
                        <h3
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                            padding: 1rem 2rem;
                            margin: auto;
                            margin-left: 0;
                          "
                        >
                          ORDER IMFORMATION
                        </h3>
                      </div>
                      <table
                        class="order-details"
                        style="
                          font-family: Raleway;
                          letter-spacing: 0.025em;
                          box-sizing: border-box;
                          width: 80%;
                          margin: 1rem auto;
                          border: 2px solid #dbd6d6;
                          border-radius: 5px;
                          border-spacing: 0px;
                          border-collapse: separate;
                        "
                      >
                        <tbody
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                          "
                        >
                          <tr
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                            "
                          >
                            <th
                              class="product-heading"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Product
                              </p>
                            </th>
                            <th
                              class="quantity-heading"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Quantity
                              </p>
                            </th>
                            <th
                              class="price-heading"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Price
                              </p>
                            </th>
                          </tr>
                          {CART_ITEMS}
                          <tr
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                            "
                          >
                            <th
                              class="subtotal"
                              colspan="2"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Subtotal:
                              </p>
                            </th>
                            <td
                              class="subtotal-price"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  margin: 1em;
                                  word-break: break-all;
                                  font-size: 0.75em;
                                "
                              >
                                $ {SUBTOTAL}
                              </p>
                            </td>
                          </tr>
                          <tr
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                            "
                          >
                            <th
                              class="shipping"
                              colspan="2"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Shipping:
                              </p>
                            </th>
                            <td
                              class="shipping-price"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  margin: 1em;
                                  word-break: break-all;
                                  font-size: 0.75em;
                                "
                              >
                                $ {SHIPPING}
                              </p>
                            </td>
                          </tr>
                          <tr
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                            "
                          >
                            <th
                              class="total"
                              colspan="2"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  font-size: 0.75em;
                                  margin-left: 1rem;
                                  font-weight: bold;
                                "
                              >
                                Total:
                              </p>
                            </th>
                            <td
                              class="total-price"
                              style="
                                font-family: Raleway;
                                letter-spacing: 0.025em;
                                box-sizing: border-box;
                                min-width: 100px;
                                height: 50px;
                                border: 1px solid #dbd6d6;
                                text-align: left;
                                width: 33.33%;
                              "
                            >
                              <p
                                style="
                                  font-family: Raleway;
                                  letter-spacing: 0.025em;
                                  box-sizing: border-box;
                                  margin: 1em;
                                  word-break: break-all;
                                  font-size: 0.75em;
                                "
                              >
                                $ {TOTAL}
                              </p>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                      <div
                        class="shipping-address"
                        style="
                          font-family: Raleway;
                          letter-spacing: 0.025em;
                          box-sizing: border-box;
                          padding: 1rem 0;
                          width: 100%;
                        "
                      >
                        <h3
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                            font-weight: 700;
                            padding: 0 2rem;
                          "
                        >
                          Shipping Address
                        </h3>
                        <div
                          class="address"
                          style="
                            font-family: Raleway;
                            letter-spacing: 0.025em;
                            box-sizing: border-box;
                            width: 80%;
                            margin: auto;
                            min-height: 100px;
                            border: 2px dashed #dbd6d6;
                            border-radius: 5px;
                          "
                        >
                          <p
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                              font-size: 0.75em;
                              font-style: italic;
                              padding-left: 1em;
                            "
                          >
                            {NAME_FOR_ORDER}
                          </p>
                          <p
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                              font-size: 0.75em;
                              font-style: italic;
                              padding-left: 1em;
                            "
                          >
                            {ADDRESS_FOR_ORDER}
                          </p>
                          <p
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                              font-size: 0.75em;
                              font-style: italic;
                              padding-left: 1em;
                            "
                          >
                            {EMAIL_FOR_ORDER}
                          </p>
                          <p
                            style="
                              font-family: Raleway;
                              letter-spacing: 0.025em;
                              box-sizing: border-box;
                              font-size: 0.75em;
                              font-style: italic;
                              padding-left: 1em;
                            "
                          >
                            {NUMBER_FOR_ORDER}
                          </p>
                        </div>
                      </div>
                      <h3
                        class="order-footer"
                        style="
                          font-family: Raleway;
                          letter-spacing: 0.025em;
                          box-sizing: border-box;
                          text-align: center;
                          font-weight: 300;
                          letter-spacing: 0.1em;
                          margin: 2rem 2rem 3rem;
                        "
                      >
                        Thank you for your shopping with us!
                      </h3>
                    </div>
                  </body>
                </html>
                        
            """;

    private String itemsHtmlTemplate = """
                        <tr
                class="item-details"
                style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                min-height: 50px;
                width: 100%;
                "
                        >
                    <td
                class="item-name"
                style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                min-width: 100px;
                height: 50px;
                border: 1px solid #dbd6d6;
                text-align: left;
                width: 33.33%;
                "
                        >
                      <p
                        style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                margin: 1em;
                word-break: break-all;
                font-size: 0.75em;
                "
                        >
                        {ITEM_NAME}
                        </p>
                    </td>
                    <td
                class="item-quantity"
                style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                min-width: 100px;
                height: 50px;
                border: 1px solid #dbd6d6;
                text-align: left;
                height: 100%;
                width: 100%;
                width: 33.33%;
                "
                        >
                      <p
                        style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                margin: 1em;
                word-break: break-all;
                font-size: 0.75em;
                "
                        >
                        {ITEM_QUANTITY}
                        </p>
                    </td>
                    <td
                class="item-price"
                style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                min-width: 100px;
                height: 50px;
                border: 1px solid #dbd6d6;
                text-align: left;
                height: 100%;
                width: 100%;
                width: 33.33%;
                "
                        >
                      <p
                        style="
                font-family: Raleway;
                letter-spacing: 0.025em;
                box-sizing: border-box;
                margin: 1em;
                word-break: break-all;
                font-size: 0.75em;
                "
                        >
                        {ITEM_PRICE}
                        </p>
                    </td>
                  </tr>
                  """;
}
