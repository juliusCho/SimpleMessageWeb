import MessageListItem from './MessageListItem.js';

export default {
    name: 'MessageList',
    components: {
        MessageListItem
    },
    template: `<ul>
        <message-list-item v-for="item in items" :key="item.id" :item="item" @delete="deleteMessage(item)"></message-list-item>
        </ul>`,
    props: {
        items: {
            type: Array,
            required: true,
            default: () => []
        }
    },
    methods: {
        deleteMessage(message) {
            this.$emit('delete', message);
        }
    }
};