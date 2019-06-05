import React from 'react';

const list = [
{
  id: 'US105',
    us: 'Add a new room to the house.'
},
{
  id: 'US108',
    us: 'Get a list of existing rooms.'
},
{
  id: 'US109',
    us: 'Edit the configuration of an existing room.'
},
{
  id: 'US130',
    us: 'Create a new house grid.'
},
{
  id: 'US145',
    us: 'Get a list of existing rooms attached to a house grid.'
},
{
  id: 'US147',
    us: 'Attach a room to a house grid.'
},
{
  id: 'US149',
    us: 'Detach a room from a house grid.'
},
];

const UserStoryList = () => (
  <ul>
    {list.map(item => (
      <li key={item.id}>
        <div>{item.us} ({item.id})</div>
      </li>
    ))}
  </ul>
);

export default UserStoryList;
