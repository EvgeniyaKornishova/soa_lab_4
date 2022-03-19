<template lang="pug">
div
  //- Вывести количество людей с заданным цветом волос
  v-card.mb-5
    v-card-title Get amount of persons with current hair color
      v-spacer
      v-btn.mb-10(color="primary", dark, @click="getAmountOfPersonsByColor()") Get
    v-card-text
      v-select(:items="availableColors", v-model="colorForAmount", label="Hair color")
      v-text-field(v-model="amountByColor", label="Amount of person", disabled)

  //- Вывести долю людей с заданным цветом волос в общей популяции (в процентах)
  v-card
    v-card-title Get % of persons with current hair color
      v-spacer
      v-btn.mb-10(color="primary", dark, @click="getPerсentsOfPersonsByColor()") Get
    v-card-text
      v-select(:items="availableColors", v-model="colorForPerсents", label="Hair color")
      v-text-field(label="% of person", v-model="perсentsByColor", disabled)

</template>

<script>
var { parseString } = require("xml2js");

export const URL_BASE = "https://localhost:5052/demography/hair-color";

export default {
  data() {
    return {
      availableColors: ["GREEN", "WHITE", "BLACK", "YELLOW", "ORANGE"],
      colorForAmount: null,
      colorForPerсents: null,
      amountByColor: null,
      perсentsByColor: null
    };
  },
  methods: {
    async getAmountOfPersonsByColor() {
      if (!this.colorForAmount)
        return;

      const response = await this.$axios.$get(
        `${URL_BASE}/` + this.colorForAmount
      );

      let amountByColor;
      parseString(response, { explicitArray: false }, function(err, result) {
        amountByColor = result.server_response.body._;
      });

      this.amountByColor = amountByColor;
    },

    async getPerсentsOfPersonsByColor() {
      if (!this.colorForPerсents)
        return;

      const response = await this.$axios.$get(
        `${URL_BASE}/` + this.colorForPerсents + `/percentage`
      );

      let perсentsByColor;
      parseString(response, { explicitArray: false }, function(err, result) {
        perсentsByColor = result.server_response.body._;
      });

      this.perсentsByColor = perсentsByColor;
    }
  },
  created(){
    this.$axios.setHeader('Content-Type', 'application/xml', ['post']);
  }
};
</script>
