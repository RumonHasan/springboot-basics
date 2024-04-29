/* eslint-disable react/prop-types */

const Task = (props) => {
  const { id, taskTitle, completed, categoryTask, deleteTask } = props;
  return (
    <div key={id} style={{ display: 'flex', gap: '10px' }}>
      <span>{taskTitle}</span>
      <span>{categoryTask}</span>
      <div>
        <span>Check for completion</span>
        <input type="checkbox" value={completed} />
      </div>
      <button onClick={() => deleteTask(id)}>Delete Task</button>
    </div>
  );
};

export default Task;
