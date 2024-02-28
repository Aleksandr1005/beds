const BASE_URL = "/api/v1";

let app = new Vue({
    el: '#app',
    vuetify : new Vuetify(),
    data: {
        loader: false,
        logs: [],

        headers: [
            { text: 'Дата', value: 'date', width: '5%' },
            { text: 'Таблица', value: 'table', width: '10%' },
            { text: 'Действие', value: 'action', width: '10%' },
            { text: 'ФИО/название', value: 'object', width: '15%' },
            { text: 'Содержание', value: 'data', width: '35%'},
            { text: 'Изменил', value: 'employee', width: '10%' },
            { text: '№ записи', value: 'recordId', width: '5%' },
        ],

        dates: [new Date().toISOString().split('T')[0], new Date().toISOString().split('T')[0]],
        table: '',
        action: '',
        object: '',
        data: '',
        employee: '',
        recordId: '',
        datesMenu: false,

        innerSearch: '',
    },
    methods: {
        search() {
            this.logs = []
            this.loader = true

            fetch(BASE_URL,
                {
                        headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "POST",
                    body: JSON.stringify({
                        dateFrom: (this.dates[0] ? this.dates[0] : new Date()),
                        dateTo: (this.dates[1] ? this.dates[1] : new Date()),
                        action: this.action,
                        table: this.table,
                        object: this.object,
                        data: this.data,
                        employee: this.employee,
                        recordId: this.recordId,
                    })
                })
                .then(response => response.json())
                .then(data => {
                    data.forEach(log => this.logs.push(log))
                    this.loader = false
                })
        },
        searchNotes() {
            this.logs = []
            this.loader = true
            fetch(BASE_URL + '/notes?patient_id=' + patientId + '&debt_id=' + debtId)
                .then(response => response.json())
                .then(data => {
                    data.forEach(log => this.logs.push(log))
                    this.loader = false
                })
        },
        searchEmails() {
            this.logs = []
            this.loader = true
            fetch(BASE_URL + '/emails?object=' + object)
                .then(response => response.json())
                .then(data => {
                    data.forEach(log => this.logs.push(log))
                    this.loader = false
                })
        },
    },
    computed: {
        datesRangeText () {
            return this.dates ? moment(this.dates[0]).format('DD.MM.YYYY') + ' ~ ' + moment(this.dates[1]).format('DD.MM.YYYY'): ''
        },
    },
    mounted() {
        if (mode === 'notes'){
            this.searchNotes();
        } else if (mode === 'emails') {
            this.searchEmails();
        }
    }
});
