import lifecycleLogger from '../mixins/lifecycle-logger.mixin.js';

export default {
    name: 'MessageListItem',
    mixins: [lifecycleLogger],
    template: `<li>{{ item.text }} - {{ item.createdAt | datetime }}
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