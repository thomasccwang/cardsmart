<%@ page import="com.cardsmart.CardReward" %>



<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="cardReward.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${cardRewardInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'startMonth', 'error')} required">
	<label for="startMonth">
		<g:message code="cardReward.startMonth.label" default="Start Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="startMonth" from="${1..12}" class="range" required="" value="${fieldValue(bean: cardRewardInstance, field: 'startMonth')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'endMonth', 'error')} required">
	<label for="endMonth">
		<g:message code="cardReward.endMonth.label" default="End Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="endMonth" from="${1..12}" class="range" required="" value="${fieldValue(bean: cardRewardInstance, field: 'endMonth')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'card', 'error')} required">
	<label for="card">
		<g:message code="cardReward.card.label" default="Card" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="card" name="card.id" from="${com.cardsmart.Card.list()}" optionKey="id" required="" value="${cardRewardInstance?.card?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardRewardInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="cardReward.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${com.cardsmart.YelpCategory.list()}" optionKey="id" required="" value="${cardRewardInstance?.category?.id}" class="many-to-one"/>
</div>

