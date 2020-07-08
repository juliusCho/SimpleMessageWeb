import MessageListItem from './MessageListItem.js';
import lifecycleLogger from '../mixins/lifecycle-logger.mixin.js';

export default {
    name: 'MessageList',
    mixins: [lifecycleLogger],
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