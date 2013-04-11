<%@ page import="com.cardsmart.YelpCategory" %>



<div class="fieldcontain ${hasErrors(bean: yelpCategoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="yelpCategory.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${yelpCategoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: yelpCategoryInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="yelpCategory.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${yelpCategoryInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: yelpCategoryInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="yelpCategory.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${com.cardsmart.YelpCategory.list()}" optionKey="id" value="${yelpCategoryInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: yelpCategoryInstance, field: 'children', 'error')} ">
	<label for="children">
		<g:message code="yelpCategory.children.label" default="Children" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${yelpCategoryInstance?.children?}" var="c">
    <li><g:link controller="yelpCategory" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="yelpCategory" action="create" params="['yelpCategory.id': yelpCategoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'yelpCategory.label', default: 'YelpCategory')])}</g:link>
</li>
</ul>

</div>

