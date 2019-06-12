import React, {Component} from 'react';

class TableHeaderUS108 extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const {headers} = this.props;
    return (
      <thead>
      <tr>
        <th>{headers.name}</th>
        <th>{headers.floor}</th>
        <th>{headers.height}</th>
        <th>{headers.length}</th>
        <th>{headers.width}</th>
        <th>{headers.edit}</th>
      </tr>
      </thead>
    );
  }
}

export default TableHeaderUS108;
