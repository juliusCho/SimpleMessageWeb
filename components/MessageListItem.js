export default {
    name: 'MessageListItem',
    template: `<li>{{ item.text }} - {{ item.createdAt }}
    <button type="button" @click="deleteClicked"></button></li>`,
    props: {
        item: {
            type: Object,
            required: true,
            default: () => {}
        }
    },
    methods: {
        deleteClicked() {
            this.$emit('delete');
        }
    }
};